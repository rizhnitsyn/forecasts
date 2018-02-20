package by.forecasts.dao;

import by.forecasts.entities.Group;

import java.util.List;

public interface GroupDao {

    List<Group> getGroupsOfTournament(Long tournamentId);
}
