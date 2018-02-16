package by.forecasts.dao;

import by.forecasts.entities.Team;
import by.forecasts.entities.Tournament;
import by.forecasts.entities.User;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

public class TournamentDaoTest extends BaseTest {

    @Test
    public void getTournamentsByUserTest() {
        Team team1 = new Team("France");
        Team team2 = new Team("Spain");
        teamDao.save(team1);
        teamDao.save(team2);
        Tournament tournament1 = new Tournament("Tournament 1", team1, LocalDate.now(), 1L);
        Tournament tournament2 = new Tournament("Tournament 2", team2, LocalDate.now(), 1L);
        tournamentDao.save(tournament1);
        tournamentDao.save(tournament2);
        User user = new User("Andrei", "Rizhnitsyn", "ra@bsb.by", 1L, "log", "pass");
        userDao.save(user);
        userDao.registerOnTournament(tournament1, user);
        userDao.registerOnTournament(tournament2, user);

        List<Tournament> tournaments = tournamentDao.getTournamentsFilterByUser(user.getId());

        assertThat(tournaments, hasSize(2));
        List<String> names = tournaments.stream()
                .map(Tournament::getName)
                .collect(Collectors.toList());
        assertThat(names, containsInAnyOrder("Tournament 1", "Tournament 2"));
    }
}
