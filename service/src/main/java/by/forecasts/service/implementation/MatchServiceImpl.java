package by.forecasts.service.implementation;

import by.forecasts.dto.MatchHardViewDto;
import by.forecasts.dto.MatchShortViewDto;
import by.forecasts.entities.Forecast;
import by.forecasts.entities.Group;
import by.forecasts.entities.Match;
import by.forecasts.entities.MatchScore;
import by.forecasts.entities.MatchState;
import by.forecasts.repositories.GroupRepository;
import by.forecasts.repositories.MatchRepository;
import by.forecasts.repositories.MatchStateRepository;
import by.forecasts.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

@Transactional
@Service
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;
    private final MatchStateRepository matchStateRepository;
    private final GroupRepository groupRepository;
    private final DateTimeFormatter dateTimeFormatterOut = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm", Locale.UK);
    private final int recordsOnPage = 10;


    private final int sixPoints = 6;
    private final int fourPoints = 4;
    private final int threePoint = 3;
    private final int onePoint = 1;
    private final int zeroPoint = 0;

    @Autowired
    public MatchServiceImpl(MatchRepository matchRepository, MatchStateRepository matchStateRepository, GroupRepository groupRepository) {
        this.matchRepository = matchRepository;
        this.matchStateRepository = matchStateRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    public List<Match> findAllByGroupId(Long groupId) {
        return matchRepository.findAllByTournamentGroupsId(groupId);
    }

    @Override
    public Long findCountOfMatchesAvailableForForecasts(Long tournamentId, Long userId) {
        return matchRepository.findCountOfMatchesAvailableForForecast(tournamentId, userId);
    }

    @Override
    public List<Match> findAllByTournamentIdAndGroupId(Long tournamentId, Long groupId) {
        return matchRepository
                .findAllByTournamentIdAndGroupIdOrderByMatchDateTime(tournamentId, groupId);
    }

    @Override
    public MatchShortViewDto save(MatchShortViewDto match) {
        Group group = groupRepository.findOne(match.getGroupId());
        if (getMatchesCountInCalendar(match) >= group.getMatchesCountBetweenTeams()) {
            match.setError("Между этими командами не может быть более " + group.getMatchesCountBetweenTeams() + " матчей. "
                    + "Внесите правильный матч!");
        } else if (Objects.equals(match.getFirstTeam().getId(), match.getSecondTeam().getId())) {
            match.setError("Команда не может играть сама с собой! Внесите правильный матч!");
        } else {
            MatchState state = matchStateRepository.findOne(2L);
            match.setMatchState(state);
            Match newMatch = new Match(match);
            newMatch.setGroup(group);
            matchRepository.save(newMatch);
            match.setError("");
        }
        return match;
    }

    private Long getMatchesCountInCalendar(MatchShortViewDto match) {
        List<Long> teamList = Arrays.asList(match.getFirstTeam().getId(), match.getSecondTeam().getId());
        return matchRepository.countAllByTournamentIdAndGroupIdAndFirstTeamIdInAndSecondTeamIdIn(
                match.getTournament().getId(),
                match.getGroupId(),
                teamList,
                teamList);
    }

    @Override
    public List<Match> findMatchesAvailableForForecasts(Long tournamentId, Long userId) {
        return matchRepository.findMatchesAvailableForForecast(tournamentId, userId);
    }

    @Override
    public MatchHardViewDto findById(Long matchId, Long userId) {
        Match foundMatch = matchRepository.findOne(matchId);
        if (foundMatch == null) {
            return null;
        }
        MatchHardViewDto matchViewDto = new MatchHardViewDto(foundMatch);
        setHardViewDtoFields(matchViewDto, foundMatch, userId);
        return matchViewDto;
    }

    @Override
    public void addMatchScore(Long matchId, MatchScore matchScore) {
        Match match = matchRepository.findOne(matchId);
        match.setMatchFinalResult(matchScore);
    }

    @Override
    public List<MatchShortViewDto> findAllByTournamentIdUserId(Long tournamentId, Long userId) {
        List<Match> matches = matchRepository.findAllByTournamentId(tournamentId);
        if (matches == null) {
            return null;
        }
        return  matches.stream()
                .map(match -> {
                    MatchShortViewDto dto = new MatchShortViewDto();
                    setDtoFields(dto, match, userId);
                    return dto;
                })
                .sorted(Comparator.comparing(MatchShortViewDto::getMatchDateTime).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<MatchShortViewDto> findAllByTournamentIdUserIdPageable(Long tournamentId, Long userId, Long pageId) {
        PageRequest pageRequest = new PageRequest(pageId.intValue(), recordsOnPage);
        Page<Match> matchPage = matchRepository.findAllByTournamentIdOrderByIdDesc(tournamentId, pageRequest);
        List<Match> matches = matchPage.getContent();
        int pageCount = matchPage.getTotalPages();
        if (matches == null) {
            return null;
        }
        return matches.stream()
                .map(match -> {
                    MatchShortViewDto dto = new MatchShortViewDto();
                    setDtoFields(dto, match, userId);
                    dto.setPageCount(pageCount);
                    return dto;
                })
                .sorted(Comparator.comparing(MatchShortViewDto::getMatchDateTime).reversed())
                .collect(Collectors.toList());
    }

    private void setDtoFields(MatchShortViewDto dto, Match match, Long userId) {
        dto.setId(match.getId());
        dto.setMatchDateTime(match.getMatchDateTime());
        dto.setTournament(match.getTournament());
        dto.setMatchState(match.getMatchState());
        dto.setFirstTeam(match.getFirstTeam());
        dto.setSecondTeam(match.getSecondTeam());
        dto.setMatchScore(match.getMatchFinalResult());
        dto.setMatchDateTimeString(match.getMatchDateTime().format(dateTimeFormatterOut));

        Forecast userForecast = match.getForecasts().stream()
                .filter(forecast -> Objects.equals(forecast.getUser().getId(), userId))
                .findFirst().orElse(null);

//        List<Long> ids = Arrays.asList(match.getFirstTeam().getId(), match.getSecondTeam().getId());
//        Group group = groupRepository.findOneByTournamentIdAndTeamsInGroupIdIn(match.getTournament().getId(), ids);
////        Group teamGroup = match.getFirstTeam().getGroups().stream()
////                .filter(group -> group.getTournament() == match.getTournament())
////                .findFirst().orElse(null);
//
//        dto.setGroupName(group == null ? "" : group.getGroupName());

        dto.setCurrentUserForecast(userForecast);
        dto.setUserPoints(calculateUserPoints(match, userId));
    }

    private void setHardViewDtoFields(MatchHardViewDto matchDto, Match foundMatch, Long userId) {
        //добавить группу и тип группы!!!
        matchDto.setForecastsCount(foundMatch.getForecasts().size());
        matchDto.setStrMatchDateTime(foundMatch.getMatchDateTime().format(dateTimeFormatterOut));
        matchDto.setFirstTeamWinCount(firstTeamWinCount(foundMatch));
        matchDto.setSecondTeamWinCount(secondTeamWinCount(foundMatch));
        matchDto.setDrawCount(drawCount(foundMatch));
        matchDto.setGuessedResultsCount(guessedResultsCount(foundMatch));
        matchDto.setGuessedWinnersCount(guessedWinnersCount(foundMatch));
        matchDto.setGuessedDiffInResultsCount(guessedDiffInResultsCount(foundMatch));
        matchDto.setCurrentUserPoints(calculateUserPoints(foundMatch, userId));

        foundMatch.getForecasts().stream()
                .filter(forecast -> forecast.getUser().getId().equals(userId))
                .map(Forecast::getMatchForecast)
                .findFirst()
                .ifPresent(matchDto::setCurrentUserForecast);

        matchDto.setActiveForForecasts(canDoForecast(foundMatch));
    }

    private boolean canDoForecast(Match foundMatch) {
        return foundMatch.getMatchDateTime().compareTo(LocalDateTime.now()) > 0
                && foundMatch.getMatchState().getId() == 2L;
    }

    private int firstTeamWinCount(Match foundMatch) {
        return (int) foundMatch.getForecasts().stream()
                .filter(forecast -> (forecast.getMatchForecast().getFirstResult() > forecast.getMatchForecast().getSecondResult()))
                .count();
    }

    private int secondTeamWinCount(Match foundMatch) {
        return (int) foundMatch.getForecasts().stream()
                .filter(forecast -> (forecast.getMatchForecast().getFirstResult() < forecast.getMatchForecast().getSecondResult()))
                .count();
    }

    private int drawCount(Match foundMatch) {
        return (int) foundMatch.getForecasts().stream()
                .filter(forecast -> (Objects.equals(forecast.getMatchForecast().getFirstResult(), forecast.getMatchForecast().getSecondResult())))
                .count();
    }

    private int guessedResultsCount(Match foundMatch) {
        return foundMatch.getMatchFinalResult() == null ? 0
                : (int) foundMatch.getForecasts().stream()
                    .filter(forecast ->
                            forecast.getMatchForecast().getFirstResult().intValue() == foundMatch.getMatchFinalResult().getFirstResult().intValue()
                                &&
                            forecast.getMatchForecast().getSecondResult().intValue() == foundMatch.getMatchFinalResult().getSecondResult().intValue())
                    .count();
    }

    private int guessedWinnersCount(Match foundMatch) {
        if (foundMatch.getMatchFinalResult() == null) {
            return 0;
        }
        return (int) foundMatch.getForecasts().stream()
                    .filter(forecast -> {
                        Integer forecastFirstResult = forecast.getMatchForecast().getFirstResult();
                        Integer forecastSecondResult = forecast.getMatchForecast().getSecondResult();
                        Integer matchFirstResult = foundMatch.getMatchFinalResult().getFirstResult();
                        Integer matchSecondResult = foundMatch.getMatchFinalResult().getSecondResult();

                        return (Integer.compare(forecastFirstResult, forecastSecondResult) == Integer.compare(matchFirstResult, matchSecondResult))
                                     &&
                               (forecastFirstResult.intValue() != matchFirstResult.intValue()
                               || forecastSecondResult.intValue() != matchSecondResult.intValue())
                                     &&
                               (forecastFirstResult - forecastSecondResult) != (matchFirstResult - matchSecondResult);
                    })
                    .count();
    }

    private int guessedDiffInResultsCount(Match foundMatch) {
        if (foundMatch.getMatchFinalResult() == null) {
            return 0;
        }
        return (int) foundMatch.getForecasts().stream()
                .filter(forecast -> {
                    Integer forecastFirstResult = forecast.getMatchForecast().getFirstResult();
                    Integer forecastSecondResult = forecast.getMatchForecast().getSecondResult();
                    Integer matchFirstResult = foundMatch.getMatchFinalResult().getFirstResult();
                    Integer matchSecondResult = foundMatch.getMatchFinalResult().getSecondResult();

                    return (((forecastFirstResult - forecastSecondResult) == (matchFirstResult - matchSecondResult))
                              && (matchFirstResult - matchSecondResult) != 0)
                                    &&
                            (forecastFirstResult.intValue() !=  matchFirstResult.intValue()
                            || forecastSecondResult.intValue() != matchSecondResult.intValue());
                })
                .count();
    }

    private int calculateUserPoints(Match foundMatch, Long userId) {
        if (foundMatch.getMatchFinalResult() == null) {
            return 0;
        }
        MatchScore userForecast = foundMatch.getForecasts().stream()
                .filter(forecast -> forecast.getUser().getId().equals(userId))
                .map(Forecast::getMatchForecast)
                .findFirst().orElse(null);
        if (userForecast == null) {
            return 0;
        }
        return calculateUserPointsPerMatch(foundMatch.getMatchFinalResult(), userForecast);
    }

    @Override
    public int calculateUserPointsPerMatch(MatchScore matchScore, MatchScore userForecast) {
        if (matchScore == null) {
            return zeroPoint;
        }
        Integer matchSecondResult = matchScore.getSecondResult();
        Integer matchFirstResult = matchScore.getFirstResult();
        Integer forecastFirstResult = userForecast.getFirstResult();
        Integer forecastSecondResult = userForecast.getSecondResult();

        if (Objects.equals(matchFirstResult, forecastFirstResult) && Objects.equals(matchSecondResult, forecastSecondResult)) {
            return sixPoints;
        } else if (forecastFirstResult - forecastSecondResult == matchFirstResult - matchSecondResult && matchFirstResult - matchSecondResult != 0) {
            return fourPoints;
        } else if (forecastFirstResult - forecastSecondResult == matchFirstResult - matchSecondResult && matchFirstResult - matchSecondResult == 0) {
            return threePoint;
        } else if (Integer.compare(forecastFirstResult, forecastSecondResult) == Integer.compare(matchFirstResult, matchSecondResult)) {
            return onePoint;
        } else {
            return zeroPoint;
        }
    }

    @Override
    public List<MatchShortViewDto> findMatchesOfSelectedTeam(Long teamId, Long tournamentId) {
        List<Match> matches =
                matchRepository.findAllByTournamentIdAndFirstTeamIdOrTournamentIdAndSecondTeamId(tournamentId, teamId,tournamentId, teamId);
        return matches.stream()
                .map(match -> {
                    MatchShortViewDto dto = new MatchShortViewDto();
                    dto.setId(match.getId());
                    dto.setMatchDateTime(match.getMatchDateTime());
                    dto.setFirstTeam(match.getFirstTeam());
                    dto.setSecondTeam(match.getSecondTeam());
                    dto.setMatchScore(match.getMatchFinalResult());
                    dto.setGroupName(match.getGroup().getGroupName());
                    dto.setMatchResultInt(getGroupName(match, teamId));
                    return dto;
                })
                .collect(Collectors.toList());
    }

    private int getGroupName(Match match, Long teamId) {
        if (match.getMatchFinalResult() == null) {
            return 0;
        }
        if (match.getFirstTeam().getId().equals(teamId)) {
            if (match.getMatchFinalResult().getFirstResult() > match.getMatchFinalResult().getSecondResult()) {
                return 1;
            } else if (match.getMatchFinalResult().getFirstResult().intValue() == match.getMatchFinalResult().getSecondResult().intValue()) {
                return 2;
            } else {
                return 3;
            }
        }
        if (!match.getFirstTeam().getId().equals(teamId)) {
            if (match.getMatchFinalResult().getFirstResult() < match.getMatchFinalResult().getSecondResult()) {
                return 1;
            } else if (match.getMatchFinalResult().getFirstResult().intValue() == match.getMatchFinalResult().getSecondResult().intValue()) {
                return 2;
            } else {
                return 3;
            }
        }
        return 0;
    }
}



















