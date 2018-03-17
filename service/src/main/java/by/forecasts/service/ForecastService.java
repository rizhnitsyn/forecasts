package by.forecasts.service;

import by.forecasts.dto.ForecastFilter;
import by.forecasts.entities.Forecast;
import by.forecasts.entities.MatchScore;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ForecastService {

    Page<Forecast> getUserForecasts(ForecastFilter forecastFilter);

    Long getCountOfUserForecasts(Long tournamentId, Long userId, Long matchStateId);

    void saveForecast(MatchScore score,Long matchId,Long userId);

    List<Forecast> findAllByUserIdAndTournamentId(Long userId, Long tournamentsId);
}
