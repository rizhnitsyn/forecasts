package by.forecasts.dao;

import by.forecasts.entities.*;
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
        teamDao.save(team1);
        teamDao.save(team2);

        Tournament tournament1 = new Tournament("Tournament 1", team1, LocalDate.now(), 1L);
        tournamentDao.save(tournament1);

        User user = new User("Andrei", "Rizhnitsyn", "ra@bsb.by", 1L, "log", "pass");
        userDao.save(user);

        Match match1 = new Match(LocalDateTime.now(), 1L, team1, team2, tournament1);
        Match match2 = new Match(LocalDateTime.now(), 1L, team2, team1, tournament1);
        matchDao.save(match1);
        matchDao.save(match2);

        Forecast forecast1 = new Forecast(new MatchScore(3, 2), user, match2);
        Forecast forecast2 = new Forecast(new MatchScore(0, 0), user, match1);
        forecastDao.save(forecast1);
        forecastDao.save(forecast2);

        List<Forecast> allForecastsOfUser = forecastDao.getAllForecastsOfUser(user.getId());

        assertThat(allForecastsOfUser, hasSize(2));
        assertEquals(allForecastsOfUser.iterator().next().getUser().getFirstName(), "Andrei");
    }
}
