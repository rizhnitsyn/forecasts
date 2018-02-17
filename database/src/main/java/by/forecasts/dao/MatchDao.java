package by.forecasts.dao;

import by.forecasts.dao.common.BaseDao;
import by.forecasts.entities.Match;

import java.util.List;

public interface MatchDao extends BaseDao<Match> {

    List<Match> getMatchesForForecast(Long tournamentId, Long userId);
}
