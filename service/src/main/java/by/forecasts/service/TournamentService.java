package by.forecasts.service;

import by.forecasts.dto.TournamentShortViewDto;
import by.forecasts.entities.Tournament;

import java.util.List;

public interface TournamentService {

    TournamentShortViewDto findOne(Long id, Long userId);

    List<Tournament> findAll();

    List<Tournament> getTournamentsFilterByUser(Long userId);

    List<TournamentShortViewDto> getTournamentsFilterByState(Long stateId, Long userId);
}
