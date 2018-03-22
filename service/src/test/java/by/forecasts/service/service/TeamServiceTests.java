package by.forecasts.service.service;

import by.forecasts.entities.Team;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TeamServiceTests extends BaseServiceTest  {

    @Test
    public void findAllTest() {
        List<Team> teams = teamService.findAll();
        assertThat(teams.stream().map(Team::getTeamName)
                .collect(Collectors.toList()),
                hasItems("Россия", "Франция"));
        assertThat(teams.size(), Matchers.greaterThan(30));
    }

    @Test
    public void findOneTest() {
        Team team = teamService.findOne(1L);
        assertEquals(team.getTeamName(),"Франция");
    }

    @Test
    public void findAllRegularTeamsNotInUseInTournamentTest() {
        List<Team> teams = teamService.findAllRegularTeamsNotInUseInTournament(1L);
        assertThat(teams.stream().map(Team::getTeamName)
                        .collect(Collectors.toList()),
                hasItem("Иран"));
        assertThat(teams.size(), Matchers.greaterThan(10));
    }

    @Test
    public void findAllPlayoffTeamsNotInUseInTournamentTest() {
        Set<Team> teams = teamService.findAllPlayoffTeamsNotInUseInTournament(1L);
        assertThat(teams.stream().map(Team::getTeamName)
                        .collect(Collectors.toList()),
                hasItem("Англия"));
        assertThat(teams.size(), Matchers.greaterThan(10));
    }
}
