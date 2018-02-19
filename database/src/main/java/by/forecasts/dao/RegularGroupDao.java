package by.forecasts.dao;

import by.forecasts.dao.common.BaseDao;
import by.forecasts.entities.RegularGroup;

import java.util.List;

public interface RegularGroupDao extends BaseDao<RegularGroup>  {

    List<RegularGroup> getGroupsOfTournament(Long tournamentId);
}
