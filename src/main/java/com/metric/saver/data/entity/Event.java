package com.metric.saver.data.entity;

import com.metric.saver.data.EventType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "event")
public class Event {
    @Id
    @Column(name = "event_id")
    @GeneratedValue(generator = "sensorUuid")
    @GenericGenerator(name = "sensorUuid", strategy = "uuid2")
    private String eventId;

    @Column
    private Date at;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventType type;

    @Column
    private double temperature;

    @Column(name = "sensor_uuid")
    private String sensorUuid;

    public String getEventId() {
        return eventId;
    }

    public Date getAt() {
        return at;
    }

    public void setAt(Date at) {
        this.at = at;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getSensorUuid() {
        return sensorUuid;
    }

    public void setSensorUuid(String sensorUuid) {
        this.sensorUuid = sensorUuid;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId='" + eventId + '\'' +
                ", at=" + at +
                ", type=" + type +
                ", temperature=" + temperature +
                '}';
    }
}
