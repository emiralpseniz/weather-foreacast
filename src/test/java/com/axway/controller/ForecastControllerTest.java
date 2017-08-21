package com.axway.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by emiralpseniz on 20/08/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ForecastControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetForecastForCityWithUnit() {
        String content = this.restTemplate.getForObject("http://localhost:" + port + "/forecast/city/Istanbul?unit=metric", String.class);
        Assert.assertTrue(content.contains("city"));
        Assert.assertTrue(content.contains("unit"));
        Assert.assertTrue(content.contains("lowestTemperatures"));
        Assert.assertTrue(content.contains("highestTemperatures"));
        Assert.assertTrue(content.contains("forecasts"));
    }

    @Test
    public void testGetForecastForCityWithoutUnit() {
        String content = this.restTemplate.getForObject("http://localhost:" + port + "/forecast/city/Istanbul", String.class);
        Assert.assertTrue(content.contains("city"));
        Assert.assertTrue(content.contains("unit"));
        Assert.assertTrue(content.contains("lowestTemperatures"));
        Assert.assertTrue(content.contains("highestTemperatures"));
        Assert.assertTrue(content.contains("forecasts"));
    }

    @Test
    public void testGetForecastForMalformedURI() {
        String content = this.restTemplate.getForObject("http://localhost:" + port + "/forecast/city?unit=metric", String.class);
        Assert.assertTrue(content.contains("404"));
        Assert.assertTrue(content.contains("error"));
    }
}
