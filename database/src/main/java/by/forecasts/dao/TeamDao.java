package by.forecasts.dao;

import by.forecasts.dao.common.BaseDao;
import by.forecasts.entities.Team;


public interface TeamDao extends BaseDao<Team> {

    Team getTeamOrganizer(Long tournamentId);
}
