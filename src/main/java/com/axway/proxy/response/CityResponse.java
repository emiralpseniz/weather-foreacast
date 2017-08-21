package com.axway.proxy.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by emiralpseniz on 20/08/2017.
 */
public class CityResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("coord")
    private CoordinateResponse coordinate;

    @JsonProperty("country")
    private String country;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CoordinateResponse getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(CoordinateResponse coordinate) {
        this.coordinate = coordinate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
