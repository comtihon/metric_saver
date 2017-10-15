package com.metric.saver.data.dao;

import com.metric.saver.data.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class EventDaoService {

    @Autowired
    private EventDao eventDao;

    public List<Event> findAll() {
        Iterable<Event> itr = eventDao.findAll();
        return new ArrayList<>((Collection<Event>) itr);
    }

    public Event save(Event entity) {
        return eventDao.save(entity);
    }
}
