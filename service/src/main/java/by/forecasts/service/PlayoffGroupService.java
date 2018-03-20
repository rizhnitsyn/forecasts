package by.forecasts.service;

import by.forecasts.entities.PlayoffGroup;

import java.util.List;

public interface PlayoffGroupService {

    void save(PlayoffGroup playoffGroup, Long tournamentId);

    List<PlayoffGroup> findAllByTournamentId(Long tournamentId);
}
