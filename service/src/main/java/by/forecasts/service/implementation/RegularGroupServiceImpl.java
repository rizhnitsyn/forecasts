package by.forecasts.service.implementation;

import by.forecasts.dto.GroupDto;
import by.forecasts.dto.TeamStatDto;
import by.forecasts.entities.Match;
import by.forecasts.entities.RegularGroup;
import by.forecasts.entities.Team;
import by.forecasts.entities.Tournament;
import by.forecasts.repositories.MatchRepository;
import by.forecasts.repositories.RegularGroupRepository;
import by.forecasts.repositories.TournamentRepository;
import by.forecasts.service.RegularGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class RegularGroupServiceImpl implements RegularGroupService {

    private final RegularGroupRepository regularGroupRepository;
    private final TournamentRepository tournamentRepository;
    private final MatchRepository matchRepository;
    private final int threePoint = 3;

    @Autowired
    public RegularGroupServiceImpl(RegularGroupRepository regularGroupRepository, TournamentRepository tournamentRepository, MatchRepository matchRepository) {
        this.regularGroupRepository = regularGroupRepository;
        this.tournamentRepository = tournamentRepository;
        this.matchRepository = matchRepository;
    }

    @Override
    public void save(RegularGroup regularGroup, Long tournamentId) {
        Tournament tournament = tournamentRepository.findOne(tournamentId);
        regularGroup.setTournament(tournament);
        regularGroupRepository.save(regularGroup);
    }

    @Override
    public List<GroupDto> findAllByTournamentId(Long tournamentId) {
        List<RegularGroup> groups = regularGroupRepository.findAllByTournamentId(tournamentId);

        return groups.stream()
                .map(GroupDto::new)
                .peek(groupDto -> {
                            Set<Team> teams = groupDto.getTeamsInGroup();
                            List<TeamStatDto> dtoSet = createDtoSet(teams, groupDto);
                            groupDto.setTeams(dtoSet);
                })
                .collect(Collectors.toList());
    }

    private List<TeamStatDto> createDtoSet(Set<Team> teams, GroupDto group) {
         return teams.stream()
                .map(team -> {
                    TeamStatDto dto = new TeamStatDto();
                    dto.setId(team.getId());
                    dto.setTeamName(team.getTeamName());
                    calculateStatistic(dto, group);
                    return dto;
                })
                .sorted(Comparator.comparing(TeamStatDto::getPoints).reversed())
                .collect(Collectors.toList());
    }

    private void calculateStatistic(TeamStatDto team, GroupDto group) {
        List<Match> matches = matchRepository.findAllByGroupIdAndFirstTeamIdOrGroupIdAndSecondTeamId(group.getId(), team.getId(), group.getId(), team.getId());
        int winCount = winCount(matches, team);
        int drawCount = drawCount(matches, team);
        team.setWin(winCount);
        team.setDraw(drawCount);
        team.setLose(loseCount(matches, team));
        team.setDiff(diffInScore(matches, team));
        team.setPoints(drawCount + winCount * threePoint);
    }

    private int winCount(List<Match> matches, TeamStatDto team) {
        return matches == null ? 0
                : (int) matches.stream()
                     .filter(match -> (match.getFirstTeam().getId().intValue() == team.getId() &&  match.getMatchFinalResult().getFirstResult() > match.getMatchFinalResult().getSecondResult())
                             || (match.getSecondTeam().getId().intValue() == team.getId() &&  match.getMatchFinalResult().getFirstResult() < match.getMatchFinalResult().getSecondResult()))
                     .count();
    }

    private int drawCount(List<Match> matches, TeamStatDto team) {
        return matches == null ? 0
                : (int) matches.stream()
                     .filter(match -> (match.getFirstTeam().getId().intValue() == team.getId() && Objects.equals(match.getMatchFinalResult().getFirstResult(), match.getMatchFinalResult().getSecondResult()))
                             || (match.getSecondTeam().getId().intValue() == team.getId() && Objects.equals(match.getMatchFinalResult().getFirstResult(), match.getMatchFinalResult().getSecondResult())))
                     .count();
    }

    private int loseCount(List<Match> matches, TeamStatDto team) {
        return matches == null ? 0
                : (int) matches.stream()
                     .filter(match -> (match.getFirstTeam().getId().intValue() == team.getId() &&  match.getMatchFinalResult().getFirstResult() < match.getMatchFinalResult().getSecondResult())
                             || (match.getSecondTeam().getId().intValue() == team.getId() &&  match.getMatchFinalResult().getFirstResult() > match.getMatchFinalResult().getSecondResult()))
                     .count();
    }

    private int diffInScore(List<Match> matches, TeamStatDto team) {
        return resultPlus(matches, team) - resultMinus(matches, team);
    }

    private int resultPlus(List<Match> matches, TeamStatDto team) {
        return matches == null ? 0
                : matches.stream()
                     .map(match -> Objects.equals(match.getFirstTeam().getId(), team.getId())
                         ? match.getMatchFinalResult().getFirstResult()
                         : match.getMatchFinalResult().getSecondResult())
                     .mapToInt(res -> res)
                     .sum();
    }

    private int resultMinus(List<Match> matches, TeamStatDto team) {
        return matches == null ? 0
                : matches.stream()
                    .map(match -> Objects.equals(match.getFirstTeam().getId(), team.getId())
                            ? match.getMatchFinalResult().getSecondResult()
                            : match.getMatchFinalResult().getFirstResult())
                    .mapToInt(res -> res)
                    .sum();
    }
}






















