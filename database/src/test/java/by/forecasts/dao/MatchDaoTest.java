package by.forecasts.dao;

import by.forecasts.entities.*;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class MatchDaoTest extends BaseTest {

    @Test
    public void getMatchesForForecastsTest() {
        Team team1 = new Team("France12");
        Team team2 = new Team("Spain12");
        teamDao.save(team1);
        teamDao.save(team2);

        Tournament tournament1 = new Tournament("Tournament 12", team1, LocalDate.now(), 1L);
        tournamentDao.save(tournament1);

        User user = new User("Andrei", "Rizhnitsyn", "ra@bsb.by", 1L, "log", "pass");
        userDao.save(user);

        Match match1 = new Match(LocalDateTime.now(), 1L, team1, team2, tournament1);
        Match match2 = new Match(LocalDateTime.now(), 1L, team2, team1, tournament1);
        matchDao.save(match1);
        matchDao.save(match2);

        Forecast forecast = new Forecast(new MatchScore(3, 2), user, match1);
        forecastDao.save(forecast);
        System.out.println(user.getId());
        System.out.println(tournament1.getId());
        System.out.println(match1.getId());
        System.out.println(match2.getId());
        List<Match> matchesForForecast = matchDao.getMatchesForForecast(tournament1.getId(), user.getId());

        matchesForForecast.forEach(System.out::println);
        matchesForForecast.stream()
                .map(Match::getForecasts)
                .forEach(forecasts -> System.out.println(forecast));


        assertThat(matchesForForecast, hasSize(1));
        assertEquals(matchesForForecast.iterator().next().getFirstTeam().getTeamName(), "Spain12");
    }
}
