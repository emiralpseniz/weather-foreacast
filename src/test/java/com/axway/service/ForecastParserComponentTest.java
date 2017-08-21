package com.axway.service;

import com.axway.model.CityForecast;
import com.axway.model.Unit;
import com.axway.proxy.response.CityResponse;
import com.axway.proxy.response.ForecastResponse;
import com.axway.proxy.response.OpenWeatherMapResponse;
import com.axway.proxy.response.TemperatureResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by emiralpseniz on 20/08/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ForecastParserComponentTest {

    @Autowired
    private ForecastParserComponent parser;

    @Test
    public void testParsingResponseWithoutTemperature() {
        OpenWeatherMapResponse response = new OpenWeatherMapResponse();
        response.setCity(new CityResponse());
        response.getCity().setName("Sofia");
        response.setForecasts(Collections.emptyList());
        CityForecast forecast = parser.parseAndFilterProxyResponse(response, Unit.KELVIN);
        Assert.assertNotNull(forecast);
        Assert.assertEquals("Sofia", forecast.getCity());
        Assert.assertEquals(Unit.KELVIN, forecast.getUnit());
        Assert.assertTrue(forecast.getForecasts().isEmpty());
    }

    @Test
    public void testParsingResponseWithTemperature() {
        OpenWeatherMapResponse response = new OpenWeatherMapResponse();
        response.setCity(new CityResponse());
        response.getCity().setName("Sofia");

        ForecastResponse forecastResponse = new ForecastResponse();
        forecastResponse.setDateTime("2017-08-20 09:00:00");
        TemperatureResponse temperatureResponse = new TemperatureResponse();
        temperatureResponse.setMaximum(123.45);
        temperatureResponse.setMinimum(78.12);
        forecastResponse.setTemperature(temperatureResponse);


        ForecastResponse forecastResponse2 = new ForecastResponse();
        forecastResponse2.setDateTime("2017-08-20 12:00:00");
        TemperatureResponse temperatureResponse2 = new TemperatureResponse();
        temperatureResponse2.setMaximum(110.56);
        temperatureResponse2.setMinimum(64.45);
        forecastResponse2.setTemperature(temperatureResponse2);

        ForecastResponse forecastResponse3 = new ForecastResponse();
        forecastResponse3.setDateTime("2017-08-20 15:00:00");
        TemperatureResponse temperatureResponse3 = new TemperatureResponse();
        temperatureResponse3.setMaximum(165.12);
        temperatureResponse3.setMinimum(64.45);
        forecastResponse3.setTemperature(temperatureResponse3);

        response.setForecasts(Arrays.asList(forecastResponse, forecastResponse2, forecastResponse3));
        CityForecast forecast = parser.parseAndFilterProxyResponse(response, Unit.FAHRENHEIT);
        Assert.assertNotNull(forecast);
        Assert.assertEquals("Sofia", forecast.getCity());
        Assert.assertEquals(Unit.FAHRENHEIT, forecast.getUnit());
        Assert.assertFalse(forecast.getForecasts().isEmpty());
        Assert.assertEquals(Double.valueOf(165.12), forecast.getForecasts().get(0).getHighest());
        Assert.assertEquals(Double.valueOf(64.45), forecast.getForecasts().get(0).getLowest());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParsingResponseWithInvalidDateTime() {
        OpenWeatherMapResponse response = new OpenWeatherMapResponse();
        response.setCity(new CityResponse());
        response.getCity().setName("Sofia");

        ForecastResponse forecastResponse = new ForecastResponse();
        forecastResponse.setDateTime("2017-08 09:00");
        TemperatureResponse temperatureResponse = new TemperatureResponse();
        temperatureResponse.setMaximum(123.45);
        temperatureResponse.setMinimum(78.12);
        forecastResponse.setTemperature(temperatureResponse);

        response.setForecasts(Collections.singletonList(forecastResponse));
        parser.parseAndFilterProxyResponse(response, Unit.FAHRENHEIT);
    }

}
