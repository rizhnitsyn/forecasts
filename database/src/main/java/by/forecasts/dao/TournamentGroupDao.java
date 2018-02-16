package by.forecasts.dao;

import by.forecasts.dao.common.BaseDao;
import by.forecasts.entities.RegularGroup;
import by.forecasts.entities.TournamentGroup;

import java.util.List;

public interface TournamentGroupDao extends BaseDao<TournamentGroup> {

    List<RegularGroup> getGroupsOfTournament(Long tournamentId);
}
