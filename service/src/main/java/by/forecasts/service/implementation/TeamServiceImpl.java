package by.forecasts.service.implementation;

import by.forecasts.entities.BaseEntity;
import by.forecasts.entities.PlayoffGroup;
import by.forecasts.entities.RegularGroup;
import by.forecasts.entities.Team;
import by.forecasts.repositories.PlayoffGroupRepository;
import by.forecasts.repositories.RegularGroupRepository;
import by.forecasts.repositories.TeamRepository;
import by.forecasts.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final RegularGroupRepository regularGroupRepository;
    private final PlayoffGroupRepository playoffGroupRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository, RegularGroupRepository regularGroupRepository, PlayoffGroupRepository playoffGroupRepository) {
        this.teamRepository = teamRepository;
        this.regularGroupRepository = regularGroupRepository;
        this.playoffGroupRepository = playoffGroupRepository;
    }

    @Override
    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    @Override
    public List<Team> findAllRegularTeamsNotInUseInTournament(Long tournamentId) {
        List<RegularGroup> groupList = regularGroupRepository.findAllByTournamentId(tournamentId);
        List<Long> groupIdList = groupList.stream()
                .map(BaseEntity::getId)
                .collect(Collectors.toList());
        List<Team> exceptList = teamRepository.findAllByGroupsIdIn(groupIdList);
        return getExceptList(exceptList);
    }

    @Override
    public List<Team> findAllPlayoffTeamsNotInUseInTournament(Long tournamentId) {
        List<PlayoffGroup> groupList = playoffGroupRepository.findAllByTournamentId(tournamentId);
        List<Long> groupIdList = groupList.stream()
                .map(BaseEntity::getId)
                .collect(Collectors.toList());
        List<Team> exceptList = teamRepository.findAllByGroupsIdIn(groupIdList);
        return getExceptList(exceptList);

    }

    private List<Team> getExceptList(List<Team> exceptList) {
        if (exceptList.size() == 0) {
            return teamRepository.findAll();
        } else {
            List<Long> exceptIdList = exceptList.stream()
                    .map(BaseEntity::getId)
                    .collect(Collectors.toList());
            return teamRepository.findAllByIdNotIn(exceptIdList);
        }
    }


}
