package by.forecasts.service;

import by.forecasts.entities.Group;

import java.util.List;

public interface GroupService {

    List<Group> getAllGroupsByTournamentID(Long id);

    Group findOne(Long id);

    void addTeam(Long groupId, Long teamId);

    void delTeam(Long groupId, Long teamId);
}
