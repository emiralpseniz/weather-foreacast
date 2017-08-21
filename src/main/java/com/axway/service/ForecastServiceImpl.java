package com.axway.service;

import com.axway.model.CityForecast;
import com.axway.model.Temperature;
import com.axway.model.Unit;
import com.axway.proxy.OpenWeatherMapProxy;
import com.axway.proxy.OpenWeatherMapProxyRequest;
import com.axway.proxy.response.OpenWeatherMapResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by emiralpseniz on 20/08/2017.
 */
@Service
public class ForecastServiceImpl implements ForecastService {

    private final OpenWeatherMapProxy openWeatherMapProxy;

    private final ForecastParserComponent parser;

    @Autowired
    public ForecastServiceImpl(OpenWeatherMapProxy openWeatherMapProxy, ForecastParserComponent parser) {
        this.openWeatherMapProxy = openWeatherMapProxy;
        this.parser = parser;
    }


    @Override
    public CityForecast getForecastForCity(String city, Unit unit) {
        OpenWeatherMapProxyRequest proxyRequest = new OpenWeatherMapProxyRequest();
        proxyRequest.setCity(city).setUnit(unit);
        OpenWeatherMapResponse response = openWeatherMapProxy.getForecastUsingAPI(proxyRequest);
        CityForecast cityForecast = parser.parseAndFilterProxyResponse(response, unit);
        findHighestAndLowestForPeriod(cityForecast);
        return cityForecast;
    }

    private void findHighestAndLowestForPeriod(CityForecast cityForecast) {
        List<Double> highests = cityForecast.getForecasts().stream().map(Temperature::getHighest).sorted().collect(Collectors.toList());
        List<Double> lowests = cityForecast.getForecasts().stream().map(Temperature::getLowest).sorted().collect(Collectors.toList());
        cityForecast.setHighestTemperatures(Arrays.asList(highests.get(highests.size() - 1), highests.get(highests.size() - 2)));
        cityForecast.setLowestTemperatures(lowests.subList(0, 2));
    }


}
