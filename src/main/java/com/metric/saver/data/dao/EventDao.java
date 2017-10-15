package com.metric.saver.data.dao;

import com.metric.saver.data.entity.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventDao extends CrudRepository<Event, String> {
}
