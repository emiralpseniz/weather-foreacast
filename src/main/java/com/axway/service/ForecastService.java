package com.axway.service;

import com.axway.model.CityForecast;
import com.axway.model.Unit;

/**
 * Created by emiralpseniz on 20/08/2017.
 */
public interface ForecastService {

    CityForecast getForecastForCity(String city, Unit unit);
}
