package by.forecasts.dao;

import by.forecasts.entities.Team;


public interface TeamDao {

    Team getTeamOrganizer(Long tournamentId);
}
