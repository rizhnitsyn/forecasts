package by.forecasts.dao;

import by.forecasts.entities.Tournament;

import java.util.List;

public interface TournamentDao {

    List<Tournament> getTournamentsFilterByUser(Long userId);
}
