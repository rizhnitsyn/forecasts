package by.forecasts.service;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ForecastServiceTest extends BaseServiceTest {

    @Test
    public void getUserForecastsTest() {
        List<Object[]> userForecasts = forecastService.getUserForecasts(1L, 1L, 2L, 5, 2);
        Assert.assertThat(userForecasts, Matchers.hasSize(5));
    }

    @Test
    public void getCountOfUserForecastsTest() {
        Long countOfUserForecasts = forecastService.getCountOfUserForecasts(1L, 1L, 2L);
        System.out.println(countOfUserForecasts);

        Assert.assertThat(countOfUserForecasts.intValue(), Matchers.greaterThan(30));
    }
}
