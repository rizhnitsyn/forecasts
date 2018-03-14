package by.forecasts.service;

import by.forecasts.entities.Team;

import java.util.List;

public interface TeamService {

    List<Team> findAll();

    List<Team> findAllRegularTeamsNotInUseInTournament(Long tournamentId);

    List<Team> findAllPlayoffTeamsNotInUseInTournament(Long tournamentId);

    List<Team> findAllTeamsByGroupId(Long groupId);

}
