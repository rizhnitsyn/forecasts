package by.forecasts.repositories;

import by.forecasts.dto.MatchHardViewDto;
import by.forecasts.dto.MatchShortViewDto;
import by.forecasts.entities.*;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class MatchTest extends BaseTest {

    @Test
    public void getMatchesForForecastsTest() {
        Team team1 = new Team("France12");
        Team team2 = new Team("Spain12");
        teamRepository.save(team1);
        teamRepository.save(team2);

        TournamentState tournamentState = new TournamentState("active");
        tournamentStateRepository.save(tournamentState);
        Tournament tournament1 = new Tournament("Tournament 12", team1, LocalDate.now(), tournamentState);
        tournamentRepository.save(tournament1);

        UserState userState = new UserState("admin");
        userStateRepository.save(userState);
        User user = new User("Andrei_log", "Rizhnitsyn", "ra2@bsb.by", userState, "log", "pass");
        userRepository.save(user);

        MatchState matchState = new MatchState("active match");
        matchState.setMatchState("active match");
        matchStateRepository.save(matchState);
        String matchState2 = matchState.getMatchState();
        RegularGroup regularGroup1 = new RegularGroup(4, 2, 2,
                "group 2", tournament1);
        regularGroupRepository.save(regularGroup1);
        Match match1 = new Match(LocalDateTime.now(), matchState, team1, team2, tournament1);
        Match match2 = new Match(LocalDateTime.now(), matchState, team2, team1, tournament1);
        match1.setSecondTeam(team2);
        match1.setMatchState(matchState);
        LocalDateTime now = LocalDateTime.now();
        match1.setMatchDateTime(LocalDateTime.now());
        Team secondTeam = match1.getSecondTeam();
        MatchState matchState1 = match1.getMatchState();
        LocalDateTime matchDateTime = match1.getMatchDateTime();
        assertEquals(secondTeam, team2);
        assertEquals(matchState1, matchState);
        assertEquals(matchDateTime, now);

        match1.setGroup(regularGroup1);
        match2.setGroup(regularGroup1);
        matchRepository.save(match1);
        matchRepository.save(match2);

        MatchShortViewDto dto = new MatchShortViewDto();
        dto.setGroupName("A");
        dto.setPageCount(2);
        dto.setUserPoints(3);
        dto.setError("error");
        dto.setGroupId(2L);
        dto.setMatchResultInt(23);
        dto.setMatchDateTimeString("date");
        dto.setId(2L);
        String groupName = dto.getGroupName();
        int pageCount = dto.getPageCount();
        int userPoints = dto.getUserPoints();
        String error = dto.getError();
        Long groupId = dto.getGroupId();
        int matchResultInt = dto.getMatchResultInt();
        String matchDateTimeString = dto.getMatchDateTimeString();
        Long id = dto.getId();

        MatchHardViewDto matchHardViewDto = new MatchHardViewDto(match1);

        MatchScore matchScore = new MatchScore(3, 2);
        matchScore.setSecondResult(2);
        Integer secondResult = matchScore.getSecondResult();
        assertThat(secondResult, equalTo(2));
        Forecast forecast = new Forecast(matchScore, user, match1);
        match1.addForecast(forecast);
        forecastRepository.save(forecast);
        List<Match> matchesForForecast = matchRepository
                .findMatchesAvailableForForecast(tournament1.getId(), user.getId());

        List<Match> matchesForForecast2 = matchRepository
                .findAllByTournamentIdAndForecastsUserId(tournament1.getId(), user.getId());

        assertThat(matchesForForecast, hasSize(1));
        assertEquals(matchesForForecast.iterator().next().getTournament().getName(), matchesForForecast2.iterator().next().getTournament().getName());
        assertEquals(matchesForForecast.iterator().next().getFirstTeam().getTeamName(), "Spain12");
    }


}
