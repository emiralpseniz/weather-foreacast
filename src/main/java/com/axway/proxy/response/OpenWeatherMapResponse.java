package com.axway.proxy.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by emiralpseniz on 20/08/2017.
 */
public class OpenWeatherMapResponse {

    @JsonProperty("cod")
    private String code;

    @JsonProperty("message")
    private Integer message;

    @JsonProperty("cnt")
    private Integer count;

    @JsonProperty("city")
    private CityResponse city;

    @JsonProperty("list")
    private List<ForecastResponse> forecasts;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getMessage() {
        return message;
    }

    public void setMessage(Integer message) {
        this.message = message;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public CityResponse getCity() {
        return city;
    }

    public void setCity(CityResponse city) {
        this.city = city;
    }

    public List<ForecastResponse> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<ForecastResponse> forecasts) {
        this.forecasts = forecasts;
    }
}
