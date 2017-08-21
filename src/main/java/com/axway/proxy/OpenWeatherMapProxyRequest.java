package com.axway.proxy;

import com.axway.model.Unit;

public class OpenWeatherMapProxyRequest {

    private String city;
    private Unit unit;

    public String getCity() {
        return city;
    }

    public OpenWeatherMapProxyRequest setCity(String city) {
        this.city = city;
        return this;
    }

    public Unit getUnit() {
        return unit;
    }

    public OpenWeatherMapProxyRequest setUnit(Unit unit) {
        this.unit = unit;
        return this;
    }

    String toQueryParameters() {
        StringBuilder builder = new StringBuilder();

        if (city != null) {
           builder.append("&q=").append(city);
        }

        if (unit != null && !"".equals(unit.toString())) {
            builder.append("&units=").append(unit.toString());
        }

        return builder.toString();
    }
}
