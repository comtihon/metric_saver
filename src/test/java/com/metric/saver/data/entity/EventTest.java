package com.metric.saver.data.entity;

import com.metric.saver.data.EventType;
import com.metric.saver.data.dao.EventDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EventTest {

    @Autowired
    private EventDao eventDao;

    @Test
    public void testCR() {
        Date now = new Date();
        Event event = new Event();
        event.setType(EventType.TEMPERATURE_EXCEEDED);
        event.setAt(now);
        event.setSensorUuid("uuid1");
        event.setTemperature(10.1);
        eventDao.save(event);

        Event loaded = eventDao.findOne(event.getEventId());
        Assert.assertEquals(now, loaded.getAt());
        Assert.assertEquals(10.1, loaded.getTemperature(), 0.1);
        Assert.assertEquals(EventType.TEMPERATURE_EXCEEDED, loaded.getType());
        Assert.assertEquals("uuid1", loaded.getSensorUuid());
    }
}