package by.forecasts.service.implementation;

import by.forecasts.entities.BaseEntity;
import by.forecasts.entities.RegularGroup;
import by.forecasts.entities.Team;
import by.forecasts.repositories.PlayoffGroupRepository;
import by.forecasts.repositories.RegularGroupRepository;
import by.forecasts.repositories.TeamRepository;
import by.forecasts.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@CacheConfig(cacheNames = "teams")
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
    @Cacheable
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
    public Set<Team> findAllPlayoffTeamsNotInUseInTournament(Long tournamentId) {
          return teamRepository.findAllByGroupsTournamentIdOrderByTeamName(tournamentId);
    }

    @Override
    @Cacheable
    public List<Team> findAllTeamsByGroupId(Long groupId) {
        return teamRepository.findAllByGroupsId(groupId);
    }

    @Override
    public Team findOne(Long id) {
        return teamRepository.findOne(id);
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
