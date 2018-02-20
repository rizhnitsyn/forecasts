package by.forecasts.dao;

import by.forecasts.entities.Match;

import java.util.List;

public interface MatchDao  {

    List<Match> getMatchesForForecast(Long tournamentId, Long userId);
}
