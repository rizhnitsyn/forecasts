package by.forecasts.dao;

import by.forecasts.entities.Team;
import by.forecasts.entities.Tournament;
import by.forecasts.entities.User;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

public class TournamentDaoTest extends BaseTest {

    @Test
    public void getTournamentsByUser() {
        Team team1 = new Team("France");
        Team team2 = new Team("Spain");
        TEAM_DAO.save(team1);
        TEAM_DAO.save(team2);
        Tournament tournament1 = new Tournament("Tournament 1", team1, LocalDate.now(), 1L);
        Tournament tournament2 = new Tournament("Tournament 2", team2, LocalDate.now(), 1L);
        TOURNAMENT_DAO.save(tournament1);
        TOURNAMENT_DAO.save(tournament2);
        User user = new User("Andrei", "Rizhnitsyn", "ra@bsb.by", 1L, "log", "pass");
        USER_DAO.save(user);
        USER_DAO.registerOnTournament(tournament1, user);
        USER_DAO.registerOnTournament(tournament2, user);

        List<Tournament> tournaments = TOURNAMENT_DAO.getTournamentsFilterByUser(user.getId());

        assertThat(tournaments, hasSize(2));
        List<String> names = tournaments.stream()
                .map(Tournament::getName)
                .collect(Collectors.toList());
        assertThat(names, contains("Tournament 1", "Tournament 2"));
    }
}
