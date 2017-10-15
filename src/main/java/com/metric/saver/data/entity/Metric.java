package com.metric.saver.data.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "metrics")
public class Metric {
    @Id
    @Column(name = "metric_id")
    @GeneratedValue(generator = "sensorUuid")
    @GenericGenerator(name = "sensorUuid", strategy = "uuid2")
    private String metricId;

    @Column(name = "sensor_uuid")
    private String sensorUuid;

    @Column
    private Date at;

    @Column
    private double temperature;

    public Metric() {
    }

    public Metric(double temperature) {
        this.temperature = temperature;
    }

    public String getMetricId() {
        return metricId;
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

    public Date getAt() {
        return at;
    }

    public void setAt(Date at) {
        this.at = at;
    }
}
