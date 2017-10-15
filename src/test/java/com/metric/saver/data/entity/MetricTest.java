package com.metric.saver.data.entity;

import com.metric.saver.data.dao.MetricDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

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
}