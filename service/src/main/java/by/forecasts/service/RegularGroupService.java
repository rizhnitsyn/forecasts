package by.forecasts.service;


import by.forecasts.entities.RegularGroup;

import java.util.List;

public interface RegularGroupService {

    void save(RegularGroup regularGroup, Long tournamentId);

    List<RegularGroup> findAllByTournamentId(Long tournamentId);
}
