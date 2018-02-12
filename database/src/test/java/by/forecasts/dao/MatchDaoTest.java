package by.forecasts.dao;

import by.forecasts.entities.*;
import by.forecasts.utils.SessionManager;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
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
        Team team1 = new Team("France");
        Team team2 = new Team("Spain");
        TEAM_DAO.save(team1);
        TEAM_DAO.save(team2);

        Tournament tournament1 = new Tournament("Tournament 1", team1, LocalDate.now(), 1L);
        TOURNAMENT_DAO.save(tournament1);

        User user = new User("Andrei", "Rizhnitsyn", "ra@bsb.by", 1L, "log", "pass");
        USER_DAO.save(user);

        Match match1 = new Match(LocalDateTime.now(), 1L, team1, team2, tournament1);
        Match match2 = new Match(LocalDateTime.now(), 1L, team2, team1, tournament1);
        MATCH_DAO.save(match1);
        MATCH_DAO.save(match2);

        Forecast forecast = new Forecast(new MatchScore(3, 2), user, match2);
        FORECAST_DAO.save(forecast);
        List<Match> matchesForForecast = MATCH_DAO.getMatchesForForecast(1L, 1L);

        assertThat(matchesForForecast, hasSize(1));
        assertEquals(matchesForForecast.iterator().next().getFirstTeam().getTeamName(), "Spain");
    }
}
