package by.forecasts.dao;

import by.forecasts.dao.common.BaseDao;
import by.forecasts.entities.Group;

import java.util.List;

public interface GroupDao extends BaseDao<Group> {

    List<Group> getGroupsOfTournament(Long tournamentId);
}
