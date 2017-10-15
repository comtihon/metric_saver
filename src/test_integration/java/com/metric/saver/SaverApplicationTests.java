package com.metric.saver;

import com.metric.saver.data.dao.EventDaoService;
import com.metric.saver.data.dao.MetricDaoService;
import com.metric.saver.data.dto.EventDTO;
import com.metric.saver.data.dto.TemperatureDTO;
import com.metric.saver.data.entity.Event;
import com.metric.saver.data.entity.Metric;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.listener.config.ContainerProperties;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SaverApplicationTests {

    @ClassRule
    public static KafkaEmbedded embeddedKafka =
            new KafkaEmbedded(1, true, "temperature", "events");

    @Autowired
    private EventDaoService eventDaoService;

    @Autowired
    private MetricDaoService metricDaoService;

    @Autowired
    private KafkaConfiguration kafkaConfiguration;
    private KafkaTemplate<String, Object> template;
    private KafkaMessageListenerContainer<String, TemperatureDTO> container;

    @Before
    public void setUp() throws Exception {
        ProducerFactory<String, Object> producerFactory =
                kafkaConfiguration.producerFactory(embeddedKafka);

        template = new KafkaTemplate<>(producerFactory);
        template.setDefaultTopic("temperature");
        DefaultKafkaConsumerFactory<String, TemperatureDTO> consumerFactory =
                kafkaConfiguration.consumerFactory(embeddedKafka);
        ContainerProperties containerProperties = new ContainerProperties("events");

        container = new KafkaMessageListenerContainer<>(consumerFactory, containerProperties);
        container.setupMessageListener((MessageListener<String, EventDTO>) System.out::println);
        container.start();

        ContainerTestUtils.waitForAssignment(container, embeddedKafka.getPartitionsPerTopic());
    }

    @After
    public void tearDown() {
        container.stop();
    }

    @Test
    public void saveMetrics() throws InterruptedException {
        for (int i = 0; i < 6; i++)
            template.sendDefault(new TemperatureDTO("uuid1", 90 + i));
        for (int i = 0; i < 2; i++)
            template.send("events", new EventDTO("uuid1", 90 + i * 3));
        Thread.sleep(1000);
        List<Metric> metrics = metricDaoService.findAll();
        metrics.sort((Metric a, Metric b) -> (int) (a.getTemperature() - b.getTemperature()));
        Assert.assertEquals(6, metrics.size());
        for (int i = 0; i < 6; i++)
            Assert.assertEquals(90 + i, metrics.get(i).getTemperature(), 0.1);
        List<Event> events = eventDaoService.findAll();
        events.sort((Event a, Event b) -> (int) (a.getTemperature() - b.getTemperature()));
        Assert.assertEquals(2, events.size());
        for (int i = 0; i < 2; i++)
            Assert.assertEquals(90 + i * 3, events.get(i).getTemperature(), 0.1);
    }

}
