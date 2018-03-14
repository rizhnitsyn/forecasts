package by.forecasts.service;

import by.forecasts.entities.Team;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeamService {

    List<Team> findAll();

    List<Team> findAllRegularTeamsNotInUseInTournament(Long tournamentId);

    List<Team> findAllPlayoffTeamsNotInUseInTournament(Long tournamentId);

    List<Team> findAllTeamsByGroupId(Long groupId);

}
