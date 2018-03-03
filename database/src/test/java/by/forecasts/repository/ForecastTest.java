package by.forecasts.repository;

import by.forecasts.entities.Forecast;
import by.forecasts.entities.Match;
import by.forecasts.entities.MatchScore;
import by.forecasts.entities.MatchState;
import by.forecasts.entities.Team;
import by.forecasts.entities.Tournament;
import by.forecasts.entities.User;
import by.forecasts.entities.UserState;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ForecastTest extends BaseTest {

    @Test
    public void getForecastsOfUserTest() {
        Team team1 = new Team("France");
        Team team2 = new Team("Spain");
        teamRepository.save(team1);
        teamRepository.save(team2);

        Tournament tournament1 = new Tournament("Tournament 1", team1, LocalDate.now(), 1L);
        tournamentRepository.save(tournament1);

        User user = new User("Andrei", "Rizhnitsyn", "ra@bsb.by", new UserState(), "log", "pass");
        userRepository.save(user);

        Match match1 = new Match(LocalDateTime.now(), new MatchState(), team1, team2, tournament1);
        Match match2 = new Match(LocalDateTime.now(), new MatchState(), team2, team1, tournament1);
        matchRepository.save(match1);
        matchRepository.save(match2);

        Forecast forecast1 = new Forecast(new MatchScore(3, 2), user, match2);
        Forecast forecast2 = new Forecast(new MatchScore(0, 0), user, match1);
        forecastRepository.save(forecast1);
        forecastRepository.save(forecast2);

        Long count = forecastRepository
                .countAllByUserIdAndMatchTournamentIdAndMatchMatchState(user.getId(), tournament1.getId(), 1L);

        Assert.assertEquals(2L, (long) count);
    }

    @Test
    public void getPageOfUserForecasts() {
        Team team1 = new Team("France");
        Team team2 = new Team("Spain");
        teamRepository.save(team1);
        teamRepository.save(team2);

        Tournament tournament1 = new Tournament("Tournament 1", team1, LocalDate.now(), 1L);
        tournamentRepository.save(tournament1);

        User user = new User("Andrei", "Rizhnitsyn", "ra@bsb.by", new UserState(), "log", "pass");
        userRepository.save(user);

        Match match1 = new Match(LocalDateTime.now(), new MatchState(), team1, team2, tournament1);
        Match match2 = new Match(LocalDateTime.now(), new MatchState(), team2, team1, tournament1);
        matchRepository.save(match1);
        matchRepository.save(match2);

        Forecast forecast1 = new Forecast(new MatchScore(3, 2), user, match2);
        Forecast forecast2 = new Forecast(new MatchScore(0, 0), user, match1);
        forecastRepository.save(forecast1);
        forecastRepository.save(forecast2);

        PageRequest pageRequest = new PageRequest(0, 5);

        Page<Forecast> forecastPage = forecastRepository
                .findAllByUserIdAndMatchTournamentIdAndMatchMatchState(user.getId(), tournament1.getId(), 1L, pageRequest);

        System.out.println(forecastPage.getTotalPages());

        List<Forecast> content = forecastPage.getContent();
        Assert.assertEquals(2, content.size());
    }
}
