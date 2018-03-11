package by.forecasts.service.implementation;

import by.forecasts.entities.Group;
import by.forecasts.entities.Team;
import by.forecasts.repositories.GroupRepository;
import by.forecasts.repositories.TeamRepository;
import by.forecasts.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository, TeamRepository teamRepository) {
        this.groupRepository = groupRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public List<Group> getAllGroupsByTournamentID(Long id) {
        return groupRepository.getAllByTournamentId(id);
    }

    @Override
    public Group findOne(Long id) {
        return groupRepository.findOne(id);
    }

    @Override
    public void addTeam(Long groupId, Long teamId) {
        Group group = groupRepository.findOne(groupId);
        Team team = teamRepository.findOne(teamId);
        group.addTeam(team);
    }

    @Override
    public void delTeam(Long groupId, Long teamId) {
        Group group = groupRepository.findOne(groupId);
        Team team = teamRepository.findOne(teamId);
        group.delTeam(team);
    }
}
