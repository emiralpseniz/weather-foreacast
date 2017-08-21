package com.axway.proxy.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by emiralpseniz on 20/08/2017.
 */
public class TemperatureResponse {

    @JsonProperty("temp_min")
    private Double minimum;

    @JsonProperty("temp_max")
    private Double maximum;

    public Double getMinimum() {
        return minimum;
    }

    public void setMinimum(Double minimum) {
        this.minimum = minimum;
    }

    public Double getMaximum() {
        return maximum;
    }

    public void setMaximum(Double maximum) {
        this.maximum = maximum;
    }
}
