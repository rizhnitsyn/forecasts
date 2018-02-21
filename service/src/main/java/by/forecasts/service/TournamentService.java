package by.forecasts.service;

import by.forecasts.entities.Tournament;

import java.util.List;

public interface TournamentService {

    Tournament findOne(Long id);

    List<Tournament> findAll();

    List<Tournament> getTournamentsFilterByUser(Long userId);
}
