package by.forecasts.repository;

import by.forecasts.entities.*;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
        matchStateRepository.save(matchState);
        Match match1 = new Match(LocalDateTime.now(), matchState, team1, team2, tournament1);
        Match match2 = new Match(LocalDateTime.now(), matchState, team2, team1, tournament1);
        matchRepository.save(match1);
        matchRepository.save(match2);

        Forecast forecast = new Forecast(new MatchScore(3, 2), user, match1);
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
