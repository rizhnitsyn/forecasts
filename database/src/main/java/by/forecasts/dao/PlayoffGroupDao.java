package by.forecasts.dao;

import by.forecasts.entities.PlayoffGroup;

import java.util.List;

public interface PlayoffGroupDao {

    List<PlayoffGroup> getGroupsOfTournament(Long tournamentId);
}
