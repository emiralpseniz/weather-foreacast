package com.axway.proxy;

import com.axway.proxy.response.OpenWeatherMapResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("/application_test.properties")
public class OpenWeatherMapProxyTest {

    @Autowired
    private OpenWeatherMapProxy proxy;

    @Test
    public void testGetForecastForIstanbulShouldReturnForecast() {
        OpenWeatherMapProxyRequest request = new OpenWeatherMapProxyRequest().setCity("Istanbul");
        OpenWeatherMapResponse response = proxy.getForecastUsingAPI(request);
        Assert.assertEquals("Istanbul", response.getCity().getName());
        Assert.assertNotNull(response.getForecasts());
        Assert.assertFalse(response.getForecasts().isEmpty());
    }


    @Test(expected = HttpClientErrorException.class)
    public void testGetForecastForNoWhereShouldReturnBadRequest() {
        OpenWeatherMapProxyRequest request = new OpenWeatherMapProxyRequest();
        proxy.getForecastUsingAPI(request);
    }

}
