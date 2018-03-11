package by.forecasts.service;

import by.forecasts.dto.MatchShortViewDto;
import by.forecasts.entities.Match;

import java.util.List;

public interface MatchService {

    List<Match> findAllByGroupId(Long groupId);

    List<Match> findAllByTournamentIdAndGroupId(Long tournamentId, Long groupId);

    MatchShortViewDto save(MatchShortViewDto match);
}
