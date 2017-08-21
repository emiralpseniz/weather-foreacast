package com.axway.proxy;

import com.axway.proxy.response.OpenWeatherMapResponse;

public interface OpenWeatherMapProxy {

    OpenWeatherMapResponse getForecastUsingAPI(OpenWeatherMapProxyRequest request);
}
