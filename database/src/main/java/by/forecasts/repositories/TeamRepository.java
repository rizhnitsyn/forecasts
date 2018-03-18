package by.forecasts.repositories;

import by.forecasts.entities.Team;
import by.forecasts.entities.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface TeamRepository extends JpaRepository<Team, Long> {

    List<Team> findAllByIdNotIn(List<Long> teams);

    List<Team> findAllByGroupsIdIn(List<Long> groups);

    List<Team> findAllByGroupsId(Long groupId);

    Set<Team> findAllByGroupsTournamentIdOrderByTeamName(Long tournamentId);

}
