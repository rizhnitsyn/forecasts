package by.forecasts.service;

import by.forecasts.entities.Team;

import java.util.List;
import java.util.Set;

public interface TeamService {

    List<Team> findAll();

    List<Team> findAllRegularTeamsNotInUseInTournament(Long tournamentId);

    Set<Team> findAllPlayoffTeamsNotInUseInTournament(Long tournamentId);

    List<Team> findAllTeamsByGroupId(Long groupId);

}
