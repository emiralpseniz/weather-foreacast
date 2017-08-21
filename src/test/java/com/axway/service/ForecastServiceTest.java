package com.axway.service;

import com.axway.model.CityForecast;
import com.axway.model.Unit;
import com.axway.proxy.OpenWeatherMapProxy;
import com.axway.service.ForecastService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by emiralpseniz on 20/08/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("/application_test.properties")
public class ForecastServiceTest {

    @Mock
    private OpenWeatherMapProxy proxy;

    @Autowired
    private ForecastService service;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetForecastForCityWithDefaultUnit() {
        CityForecast cityForecast = service.getForecastForCity("Istanbul", Unit.KELVIN);
        Assert.assertNotNull(cityForecast);
        Assert.assertEquals("Istanbul", cityForecast.getCity());
        Assert.assertEquals(Unit.KELVIN, cityForecast.getUnit());
        Assert.assertFalse(cityForecast.getHighestTemperatures().isEmpty());
        Assert.assertFalse(cityForecast.getLowestTemperatures().isEmpty());
        Assert.assertFalse(cityForecast.getForecasts().isEmpty());
        Assert.assertEquals(6, cityForecast.getForecasts().size());
        Assert.assertTrue(cityForecast.getHighestTemperatures().get(0) >= cityForecast.getHighestTemperatures().get(1));
        Assert.assertTrue(cityForecast.getLowestTemperatures().get(0) <= cityForecast.getLowestTemperatures().get(1));

        // TODO: Check whether the two highest or lowest temperatures are really the highest and lowest in period.
    }

    @Test
    public void testGetForecastForCityWithMetricUnit() {
        CityForecast cityForecast = service.getForecastForCity("Istanbul", Unit.CELSIUS);
        Assert.assertEquals(Unit.CELSIUS, cityForecast.getUnit());
    }
}
