package by.forecasts.service;

import by.forecasts.dto.TournamentShortViewDto;
import by.forecasts.entities.Tournament;

import java.util.List;

public interface TournamentService {

    TournamentShortViewDto findOne(Long id, Long userId);

    TournamentShortViewDto findOne(Long id);

    List<Tournament> findAll();

    Tournament save(TournamentShortViewDto tournament);

    List<Tournament> getTournamentsFilterByUser(Long userId);

    List<TournamentShortViewDto> getActiveTournamentsFilterByUser(Long userId);

    List<TournamentShortViewDto> getTournamentsFilterByState(Long stateId, Long userId);

    void registerOnTournament(Long tournamentId, Long userId);

    void closeTournament(Long tournamentId);
}
