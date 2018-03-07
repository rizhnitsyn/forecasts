package by.forecasts.service.implementation;

import by.forecasts.entities.Group;
import by.forecasts.repositories.GroupRepository;
import by.forecasts.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public List<Group> getAllGroupsByTournamentID(Long id) {
        return groupRepository.getAllByTournamentId(id);
    }

    @Override
    public Group findOne(Long id) {
        return groupRepository.findOne(id);
    }
}
