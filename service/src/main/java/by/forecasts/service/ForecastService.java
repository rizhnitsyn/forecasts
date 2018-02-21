package by.forecasts.service;

import by.forecasts.entities.Forecast;

import java.util.List;

public interface ForecastService {

    List<Forecast> getUserForecasts(Long tournamentId, Long userId, Long matchStateId, int recordsCnt, int pageNo);

    Long getCountOfUserForecasts(Long tournamentId, Long userId, Long matchStateId);
}
