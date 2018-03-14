package by.forecasts.repository;

import by.forecasts.entities.Team;
import by.forecasts.entities.Tournament;
import by.forecasts.entities.TournamentState;
import by.forecasts.entities.User;
import by.forecasts.entities.UserState;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

public class TournamentTest extends BaseTest {

    @Test
    public void getTournamentsByUserTest() {
        Team team1 = new Team("France");
        Team team2 = new Team("Spain");
        teamRepository.save(team1);
        teamRepository.save(team2);
        TournamentState tournamentState = new TournamentState("active");
        tournamentStateRepository.save(tournamentState);
        Tournament tournament1 = new Tournament("Tournament 1", team1, LocalDate.now(), tournamentState);
        Tournament tournament2 = new Tournament("Tournament 2", team2, LocalDate.now(), tournamentState);
        tournamentRepository.save(tournament1);
        tournamentRepository.save(tournament2);
        UserState userState = new UserState("active user");
        userStateRepository.save(userState);
        User user = new User("Andrei", "Rizhnitsyn", "ra@bsb.by", userState, "log", "pass");
        User user2 = new User("Andrei2", "Rizhnitsyn2", "ra2@bsb.by", userState, "log2", "pass");
        userRepository.save(user);
        userRepository.save(user2);
        tournament1.getUsers().add(user);
        tournament2.getUsers().add(user);
        tournament2.getUsers().add(user2);

        List<Tournament> tournaments = tournamentRepository.getAllByUsersId(user.getId());

        System.out.println(tournaments);

        assertThat(tournaments, hasSize(2));
        List<String> names = tournaments.stream()
                .map(Tournament::getName)
                .collect(Collectors.toList());
        assertThat(names, containsInAnyOrder("Tournament 1", "Tournament 2"));
    }
}
