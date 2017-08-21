package com.axway.proxy;

import com.axway.proxy.response.OpenWeatherMapResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenWeatherMapProxyImpl implements OpenWeatherMapProxy {

    @Value("${open-weather-map.appid}")
    private String appId;

    @Value("${open-weather-map.url}")
    private String url;

    private final RestTemplate restTemplate;

    @Autowired
    public OpenWeatherMapProxyImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public OpenWeatherMapResponse getForecastUsingAPI(OpenWeatherMapProxyRequest request) {
        String completeUrl = buildUrl(request);
        return restTemplate.getForObject(completeUrl, OpenWeatherMapResponse.class);
    }

    private String buildUrl(OpenWeatherMapProxyRequest request) {
        return url + request.toQueryParameters() + "&appid=" + appId;
    }

}
