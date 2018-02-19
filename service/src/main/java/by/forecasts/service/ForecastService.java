package by.forecasts.service;

import java.util.List;

public interface ForecastService {

    List<Object[]> getUserForecasts(Long tournamentId, Long userId, Long matchStateId, int recordsCnt, int pageNo);

    Long getCountOfUserForecasts(Long tournamentId, Long userId, Long matchStateId);
}
