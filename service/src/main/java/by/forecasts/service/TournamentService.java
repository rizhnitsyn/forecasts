package by.forecasts.service;

import by.forecasts.entities.Tournament;

import java.util.List;

public interface TournamentService {

    Tournament getTournamentById(Long id);

    List<Tournament> getListOfTournaments();

    List<Tournament> getTournamentsFilterByUser(Long userId);
}
