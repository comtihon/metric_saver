package com.metric.saver.service;

import com.metric.saver.data.dto.EventDTO;
import com.metric.saver.data.dto.TemperatureDTO;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaReceiver.class);

    @Autowired
    private MetricService service;

    @KafkaListener(topics = "${spring.kafka.topic_temperature}", containerFactory = "temperature_factory")
    public void receiveTemperature(ConsumerRecord<String, TemperatureDTO> consumerRecord) throws InterruptedException {
        LOGGER.debug("got metric: '{}'", consumerRecord);
        service.saveMetric(consumerRecord.value());
    }

    @KafkaListener(topics = "${spring.kafka.topic_event}", containerFactory = "event_factory")
    public void receiveEvent(ConsumerRecord<String, EventDTO> consumerRecord) throws InterruptedException {
        LOGGER.debug("got event: '{}'", consumerRecord);
        service.saveEvent(consumerRecord.value());
    }
}
