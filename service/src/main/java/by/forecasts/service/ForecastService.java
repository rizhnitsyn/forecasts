package by.forecasts.service;

import by.forecasts.dto.ForecastFilter;
import by.forecasts.entities.Forecast;
import org.springframework.data.domain.Page;

public interface ForecastService {

    Page<Forecast> getUserForecasts(ForecastFilter forecastFilter);

    Long getCountOfUserForecasts(Long tournamentId, Long userId, Long matchStateId);
}
