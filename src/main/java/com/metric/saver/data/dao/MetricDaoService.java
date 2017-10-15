package com.metric.saver.data.dao;

import com.metric.saver.data.entity.Metric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class MetricDaoService {
    @Autowired
    private MetricDao metricDao;

    public Metric save(Metric entity) {
        return metricDao.save(entity);
    }

    public List<Metric> findAll() {
        Iterable<Metric> itr = metricDao.findAll();
        return new ArrayList<>((Collection<Metric>) itr);
    }
}
