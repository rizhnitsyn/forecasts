package by.forecasts.dao;

import by.forecasts.entities.RegularGroup;

import java.util.List;

public interface RegularGroupDao  {

    List<RegularGroup> getGroupsOfTournament(Long tournamentId);
}
