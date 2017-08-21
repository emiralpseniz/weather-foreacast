package com.axway.proxy.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by emiralpseniz on 20/08/2017.
 */
public class CoordinateResponse {

    @JsonProperty("lat")
    private Double latitude;

    @JsonProperty("lon")
    private Double longitude;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
