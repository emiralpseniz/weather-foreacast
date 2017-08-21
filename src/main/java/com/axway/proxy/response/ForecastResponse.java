package com.axway.proxy.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by emiralpseniz on 20/08/2017.
 */
public class ForecastResponse {

    @JsonProperty("dt_txt")
    private String dateTime;

    @JsonProperty("main")
    private TemperatureResponse temperature;

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public TemperatureResponse getTemperature() {
        return temperature;
    }

    public void setTemperature(TemperatureResponse temperature) {
        this.temperature = temperature;
    }

}
