package com.axway.controller;

import com.axway.model.CityForecast;
import com.axway.model.Unit;
import com.axway.service.ForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by emiralpseniz on 20/08/2017.
 *
 * Forecast API
 *
 */
@RestController
public class ForecastController {

    private final ForecastService forecastService;

    @Autowired
    public ForecastController(ForecastService forecastService) {
        this.forecastService = forecastService;
    }

    @RequestMapping(value = "/forecast/city/{city}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody CityForecast getFiveDayForecastForCity(@PathVariable String city, @RequestParam(required = false) String unit) {
        Unit temperatureUnit = Unit.valueFromString(unit);
        return forecastService.getForecastForCity(city, temperatureUnit);
    }

}
