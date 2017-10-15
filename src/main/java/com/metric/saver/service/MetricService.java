package com.metric.saver.service;

import com.metric.saver.data.dao.EventDaoService;
import com.metric.saver.data.dao.MetricDaoService;
import com.metric.saver.data.dto.EventDTO;
import com.metric.saver.data.dto.TemperatureDTO;
import com.metric.saver.data.entity.Event;
import com.metric.saver.data.entity.Metric;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MetricService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MetricService.class);

    @Autowired
    private EventDaoService eventDaoService;

    @Autowired
    private MetricDaoService metricDaoService;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public void saveMetric(TemperatureDTO dto) {
        Metric metric = modelMapper.map(dto, Metric.class);
        LOGGER.debug("Save {}", metric);
        metricDaoService.save(metric);
    }

    @Transactional
    public void saveEvent(EventDTO dto) {
        Event event = modelMapper.map(dto, Event.class);
        LOGGER.debug("Save {}", event);
        eventDaoService.save(event);
    }
}
