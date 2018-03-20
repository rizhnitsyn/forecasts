package by.forecasts.service;


import by.forecasts.dto.GroupDto;
import by.forecasts.entities.RegularGroup;

import java.util.List;

public interface RegularGroupService {

    void save(RegularGroup regularGroup, Long tournamentId);

    List<GroupDto> findAllByTournamentId(Long tournamentId);
}
