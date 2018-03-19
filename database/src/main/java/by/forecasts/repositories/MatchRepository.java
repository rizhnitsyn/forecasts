package by.forecasts.repositories;

import by.forecasts.entities.Match;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {

    @Query("select m from Match m where m.tournament.id = ?1 "
            + "and m.id not in (select f.match.id from Forecast f where f.user.id = ?2)")
    List<Match> findMatchesAvailableForForecast(Long tournamentId, Long userId);

    @Query("select count(m.id) from Match m where m.tournament.id = ?1 "
            + "and m.id not in (select f.match.id from Forecast f where f.user.id = ?2)")
    Long findCountOfMatchesAvailableForForecast(Long tournamentId, Long userId);

    List<Match> findAllByTournamentIdAndFirstTeamIdOrTournamentIdAndSecondTeamId(Long fTrId, Long firstTeamId, Long sTrId, Long secondTeamId);

    List<Match> findAllByTournamentId(Long tournamentId);

    Page<Match> findAllByTournamentIdOrderByIdDesc(Long tournamentId, Pageable pageable);

    List<Match> findAllByTournamentIdAndForecastsUserId(Long tournamentId, Long userId);

    List<Match> findAllByTournamentGroupsId(Long groupId);

//    List<Match> findAllByTournamentIdAndFirstTeamGroupsIdAndSecondTeamGroupsIdOrderByMatchDateTime(Long tournamentId, Long firstGroupId, Long secondGroupId);
    List<Match> findAllByTournamentIdAndGroupIdOrderByMatchDateTime(Long tournamentId, Long firstGroupId);

    Long countAllByTournamentIdAndGroupIdAndFirstTeamIdInAndSecondTeamIdIn(Long tournamentId, Long groupId, List<Long> firstTeamId, List<Long> secondTeamId);
}
