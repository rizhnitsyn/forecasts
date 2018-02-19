package by.forecasts.dao;

import by.forecasts.dao.common.BaseDao;
import by.forecasts.entities.PlayoffGroup;

import java.util.List;

public interface PlayoffGroupDao extends BaseDao<PlayoffGroup> {

    List<PlayoffGroup> getGroupsOfTournament(Long tournamentId);
}
