package by.forecasts.service;

import by.forecasts.dto.MatchHardViewDto;
import by.forecasts.dto.MatchShortViewDto;
import by.forecasts.entities.Match;
import by.forecasts.entities.MatchScore;

import java.util.List;

public interface MatchService {

    Match findOne(Long id);

    Long findCountOfMatchesAvailableForForecasts(Long tournamentId, Long userId);

    List<Match> findAllByTournamentIdAndGroupId(Long tournamentId, Long groupId);

    MatchShortViewDto save(MatchShortViewDto match);

    List<Match> findMatchesAvailableForForecasts(Long tournamentId, Long userId);

    MatchHardViewDto findById(Long matchId, Long userId);

    void addMatchScore(Long matchId, MatchScore matchScore);

    List<MatchShortViewDto> findAllByTournamentIdUserId(Long tournamentId, Long userId);

    List<MatchShortViewDto> findAllByTournamentIdUserIdPageable(Long tournamentId, Long userId, Long pageId);

    int calculateUserPointsPerMatch(MatchScore matchScore, MatchScore userForecast);

    List<MatchShortViewDto> findMatchesOfSelectedTeam(Long teamId, Long tournamentId);
}
