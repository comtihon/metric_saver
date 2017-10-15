package com.metric.saver.data.dto;

import java.util.Date;

public class TemperatureDTO {
    private String sensorUuid;
    private double temperature;
    private Date at;

    public TemperatureDTO() {
    }

    public TemperatureDTO(String sensorUuid, double temperature) {
        this.sensorUuid = sensorUuid;
        this.temperature = temperature;
    }

    public String getSensorUuid() {
        return sensorUuid;
    }

    public void setSensorUuid(String sensorUuid) {
        this.sensorUuid = sensorUuid;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public Date getAt() {
        return at;
    }

    public void setAt(Date at) {
        this.at = at;
    }

    @Override
    public String toString() {
        return "TemperatureDTO{" +
                "sensorUuid='" + sensorUuid + '\'' +
                ", temperature=" + temperature +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TemperatureDTO that = (TemperatureDTO) o;

        if (Double.compare(that.temperature, temperature) != 0) return false;
        return sensorUuid != null ? sensorUuid.equals(that.sensorUuid) : that.sensorUuid == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = sensorUuid != null ? sensorUuid.hashCode() : 0;
        temp = Double.doubleToLongBits(temperature);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
