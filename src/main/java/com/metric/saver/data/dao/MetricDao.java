package com.metric.saver.data.dao;

import com.metric.saver.data.entity.Metric;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricDao extends CrudRepository<Metric, String> {
}
