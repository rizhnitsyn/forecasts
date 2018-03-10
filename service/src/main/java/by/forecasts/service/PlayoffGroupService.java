package by.forecasts.service;

import by.forecasts.entities.PlayoffGroup;

public interface PlayoffGroupService {

    void save(PlayoffGroup playoffGroup, Long tournamentId);
}
