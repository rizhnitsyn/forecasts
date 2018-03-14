package by.forecasts.service;


import by.forecasts.entities.RegularGroup;

public interface RegularGroupService {

    void save(RegularGroup regularGroup, Long tournamentId);
}
