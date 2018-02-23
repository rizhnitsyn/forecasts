package by.forecasts.service;

import by.forecasts.entities.Forecast;
import org.springframework.data.domain.Page;

public interface ForecastService {

    Page<Forecast> getUserForecasts(Long tournamentId, Long userId, Long matchStateId, int recordsCnt, int pageNo);

    Long getCountOfUserForecasts(Long tournamentId, Long userId, Long matchStateId);
}
