package by.forecasts.dao;

import by.forecasts.dao.common.BaseDao;
import by.forecasts.entities.Tournament;

import java.util.List;

public interface TournamentDao extends BaseDao<Tournament> {

    List<Tournament> getTournamentsFilterByUser(Long userId);
}
