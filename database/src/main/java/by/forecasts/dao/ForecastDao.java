package by.forecasts.dao;


import by.forecasts.dao.common.BaseDao;
import by.forecasts.entities.Forecast;

import java.util.List;

public interface ForecastDao extends BaseDao<Forecast> {

    List<Forecast> getAllForecastsOfUser(Long userId);

    List<Object[]> getUserForecastsOfTournament(Long userId, Long tournamentId, Long matchState, int recordsCnt, int pageNo);

    Long getCountOfUserForecasts(Long userId, Long tournamentId, Long matchState);
}
