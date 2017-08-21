package com.axway.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 * Five day temperature forecast for a give city.
 */
public class CityForecast implements Serializable {

    private static final long serialVersionUID = 387570473513772864L;

    private String city;

    private Unit unit;

    private List<Double> lowestTemperatures;

    private List<Double> highestTemperatures;

    private List<Temperature> forecasts;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public List<Double> getLowestTemperatures() {
        return lowestTemperatures;
    }

    public void setLowestTemperatures(List<Double> lowestTemperatures) {
        this.lowestTemperatures = lowestTemperatures;
    }

    public List<Double> getHighestTemperatures() {
        return highestTemperatures;
    }

    public void setHighestTemperatures(List<Double> highestTemperatures) {
        this.highestTemperatures = highestTemperatures;
    }

    public List<Temperature> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<Temperature> forecasts) {
        this.forecasts = forecasts;
    }
}
