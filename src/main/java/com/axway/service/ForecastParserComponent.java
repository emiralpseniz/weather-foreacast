package com.axway.service;

import com.axway.model.CityForecast;
import com.axway.model.Temperature;
import com.axway.model.Unit;
import com.axway.proxy.response.ForecastResponse;
import com.axway.proxy.response.OpenWeatherMapResponse;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by emiralpseniz on 20/08/2017.
 *
 * Parses OpenWeatherMap API response.
 * Filters out highest and lowest temperatures
 * for each day in the 5 day period.
 *
 */
@Component
public class ForecastParserComponent {

    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd-MM-yyyy");
    private static final SimpleDateFormat PARSER = new SimpleDateFormat("yyyy-MM-dd");

    public CityForecast parseAndFilterProxyResponse(OpenWeatherMapResponse response, Unit unit) {
        CityForecast cf = new CityForecast();
        cf.setCity(response.getCity().getName());
        cf.setUnit(unit);
        cf.setForecasts(parseForecasts(response.getForecasts()));
        return cf;
    }

    private List<Temperature> parseForecasts(List<ForecastResponse> forecastResponses) {

        Map<String, List<Double>> dateTempMap = new HashMap<>();

        forecastResponses.forEach((forecastResponse -> {
            try {
                String date = FORMATTER.format(PARSER.parse(forecastResponse.getDateTime()));
                dateTempMap.computeIfAbsent(date, list -> new ArrayList<>());
                List<Double> temperatureList = dateTempMap.get(date);
                temperatureList.add(forecastResponse.getTemperature().getMinimum());
                temperatureList.add(forecastResponse.getTemperature().getMaximum());
            } catch (ParseException e) {
                throw new IllegalArgumentException("OpenWeatherMap API is acting weird.");
            }
        }));

        return dateTempMap.entrySet().stream().map(entry -> {
            List<Double> tempValues = entry.getValue();
            tempValues.sort(Double::compareTo);
            Double lowest = tempValues.get(0);
            Double highest = tempValues.get(tempValues.size() - 1);
            return new Temperature(entry.getKey(), lowest, highest);
        }).sorted().collect(Collectors.toList());
    }

}

