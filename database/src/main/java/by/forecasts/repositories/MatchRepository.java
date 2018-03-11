package by.forecasts.repositories;

import by.forecasts.entities.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {

    @Query("select m from Match m where m.tournament.id = ?1 "
            + "and m.id not in (select f.match.id from Forecast f where f.user.id = ?2)")
    List<Match> findMatchesAvailableForForecast(Long tournamentId, Long userId);

    List<Match> findAllByTournamentIdAndForecastsUserId(Long tournamentId, Long userId);

    List<Match> findAllByTournamentGroupsId(Long groupId);

    List<Match> findAllByTournamentIdAndFirstTeamGroupsId(Long tournamentId, Long groupId);

    Long countAllByTournamentIdAndFirstTeamGroupsIdAndFirstTeamIdInAndSecondTeamIdIn(Long tournamentId, Long groupId, List<Long> firstTeamId, List<Long> secondTeamId);
}
