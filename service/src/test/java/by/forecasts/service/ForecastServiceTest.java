package by.forecasts.service;

import by.forecasts.dto.ForecastFilter;
import by.forecasts.entities.Forecast;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.domain.Page;

import java.util.List;

public class ForecastServiceTest extends BaseServiceTest {

    @Test
    public void getUserForecastsTest() {
        ForecastFilter forecastFilter = new ForecastFilter(1L, 1L, 2L, 5, 2, 0);
        Page<Forecast> userForecasts = forecastService.getUserForecasts(forecastFilter);
        Assert.assertThat(userForecasts.getContent(), Matchers.hasSize(5));
    }

    @Test
    public void getCountOfUserForecastsTest() {
        Long countOfUserForecasts = forecastService.getCountOfUserForecasts(1L, 1L, 2L);
        Assert.assertThat(countOfUserForecasts.intValue(), Matchers.greaterThan(30));
    }
}
