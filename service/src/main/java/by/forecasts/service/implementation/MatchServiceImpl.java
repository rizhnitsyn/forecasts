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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Transactional
@Service
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;
    private final MatchStateRepository matchStateRepository;
    private final GroupRepository groupRepository;
    private final DateTimeFormatter dateTimeFormatterIn = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm", Locale.UK);
    private final DateTimeFormatter dateTimeFormatterOut = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm", Locale.UK);

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
        return matchRepository.findAllByTournamentIdAndFirstTeamGroupsId(tournamentId, groupId);
    }

    @Override
    public MatchShortViewDto save(MatchShortViewDto match) {
        setDtoFields(match);
        Group group = groupRepository.findOne(match.getGroupId());
        if (getMatchesCountInCalendar(match) >= group.getMatchesCountBetweenTeams()) {
            match.setError("Между этими командами не может быть более " + group.getMatchesCountBetweenTeams() + " матчей. "
                    + "Внесите правильный матч!");
        } else {
            Match newMatch = new Match(match);
            matchRepository.save(newMatch);
            match.setError("");
        }
        return match;
    }

    private Long getMatchesCountInCalendar(MatchShortViewDto match) {
        List<Long> teamList = Arrays.asList(match.getFirstTeam().getId(), match.getSecondTeam().getId());
        return matchRepository.countAllByTournamentIdAndFirstTeamGroupsIdAndFirstTeamIdInAndSecondTeamIdIn(
                match.getTournament().getId(),
                match.getGroupId(),
                teamList,
                teamList);
    }

    private void setDtoFields(MatchShortViewDto match) {
        MatchState state = matchStateRepository.findOne(2L);
        LocalDateTime startDate = LocalDateTime.parse(match.getMatchDateTimeString(), dateTimeFormatterIn);
        match.setMatchDateTime(startDate);
        match.setMatchState(state);
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

        //добавить группу и тип группы!!!
        matchViewDto.setForecastsCount(foundMatch.getForecasts().size());
        matchViewDto.setStrMatchDateTime(foundMatch.getMatchDateTime().format(dateTimeFormatterOut));
        matchViewDto.setFirstTeamWinCount(firstTeamWinCount(foundMatch));
        matchViewDto.setSecondTeamWinCount(secondTeamWinCount(foundMatch));
        matchViewDto.setDrawCount(drawCount(foundMatch));
        matchViewDto.setGuessedResultsCount(guessedResultsCount(foundMatch));
        matchViewDto.setGuessedWinnersCount(guessedWinnersCount(foundMatch));
        matchViewDto.setGuessedDiffInResultsCount(guessedDiffInResultsCount(foundMatch));
        matchViewDto.setCurrentUserPoints(calculateUserPoints(foundMatch, userId));

        foundMatch.getForecasts().stream()
                .filter(forecast -> forecast.getUser().getId().equals(userId))
                .map(Forecast::getMatchForecast)
                .findFirst()
                .ifPresent(matchViewDto::setCurrentUserForecast);


        return matchViewDto;
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

    private int calculateUserPointsPerMatch(MatchScore matchScore, MatchScore userForecast) {
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
}
