package com.metric.saver.data.entity;

import com.metric.saver.data.dao.MetricDao;
import com.metric.saver.data.dto.TemperatureDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MetricTest {

    @Autowired
    private MetricDao metricDao;

    @Test
    public void testCR() {
        Metric metric = new Metric(10.1);
        metric.setSensorUuid("uuid1");
        metricDao.save(metric);

        Metric loaded = metricDao.findOne(metric.getMetricId());
        Assert.assertEquals(10.1, loaded.getTemperature(), 0.1);
        Assert.assertEquals("uuid1", loaded.getSensorUuid());
    }

    @Test
    public void testSaveFromDto() {
        ModelMapper modelMapper = new ModelMapper();
        TemperatureDTO dto = new TemperatureDTO("uuid1", 10.0);
        Date current = new Date();
        dto.setAt(current);
        Metric metric = modelMapper.map(dto, Metric.class);
        metricDao.save(metric);

        Metric loaded = metricDao.findOne(metric.getMetricId());
        Assert.assertEquals(current, loaded.getAt());
    }
}