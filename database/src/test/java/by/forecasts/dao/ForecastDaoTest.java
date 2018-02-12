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

public class ForecastDaoTest extends BaseTest {

    @Test
    public void getForecastsOfUserTest() {
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

        Forecast forecast1 = new Forecast(new MatchScore(3, 2), user, match2);
        Forecast forecast2 = new Forecast(new MatchScore(0, 0), user, match1);
        FORECAST_DAO.save(forecast1);
        FORECAST_DAO.save(forecast2);

        List<Forecast> allForecastsOfUser = FORECAST_DAO.getAllForecastsOfUser(user.getId());

        assertThat(allForecastsOfUser, hasSize(2));
        assertEquals(allForecastsOfUser.iterator().next().getUser().getFirstName(), "Andrei");
    }
}
