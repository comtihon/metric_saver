package com.metric.saver.data.dto;

import com.metric.saver.data.EventType;

import java.util.Date;

public class EventDTO {
    private String sensorUuid;
    private double temperature;
    private Date at = new Date();
    private EventType type = EventType.TEMPERATURE_EXCEEDED;

    public EventDTO() {
    }

    public EventDTO(String sensorUuid, double temperature) {
        this.sensorUuid = sensorUuid;
        this.temperature = temperature;
    }

    public String getSensorUuid() {
        return sensorUuid;
    }

    public double getTemperature() {
        return temperature;
    }

    public Date getAt() {
        return at;
    }

    public EventType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "EventDTO{" +
                "sensorUuid='" + sensorUuid + '\'' +
                ", temperature=" + temperature +
                ", at=" + at +
                ", type=" + type +
                '}';
    }
}
