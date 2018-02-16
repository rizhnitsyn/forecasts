package by.forecasts.dao;

import by.forecasts.entities.Team;
import by.forecasts.entities.Tournament;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class BaseDaoTest extends BaseTest {

    @Test
    public void saveDaoTest() {
        Team team = new Team("Poland");
        Tournament tournament = new Tournament("Tournament", team, LocalDate.now(), 1L);
        teamDao.save(team);
        tournamentDao.save(tournament);
        assertEquals(team.getTeamName(), "Poland");
        assertEquals(tournament.getOrganizer().getTeamName(), "Poland");
    }

    @Test
    public void getByIdDaoTest() {
        System.out.println("start test");
        Team team = new Team("Spain");
        teamDao.save(team);
        Team foundTeam = teamDao.findById(1L);
        assertEquals(foundTeam.getTeamName(), "Spain");
        System.out.println("end test");
    }

    @Test
    public void getAllDaoTest() {
        Team team = new Team("Spain");
        Team team1 = new Team("France");
        teamDao.save(team);
        teamDao.save(team1);
        List<Team> teamList = teamDao.findAll();
        assertThat(teamList, hasSize(2));
        List<String> teamNames = teamList.stream()
                .map(Team::getTeamName)
                .collect(Collectors.toList());
        assertThat(teamNames, contains("Spain", "France"));
    }

    @Test
    public void deleteDaoTest() {
        Team team = new Team("Spain");
        teamDao.save(team);
        assertEquals(team.getTeamName(), "Spain");
        teamDao.delete(team);
        List<Team> teamList = teamDao.findAll();
        assertThat(teamList, hasSize(0));
    }

    @Test
    public void updateDaoTest() {
        Team team = new Team("Spain");
        teamDao.save(team);
        assertEquals(team.getTeamName(), "Spain");
        team.setTeamName("Updated Name");
        teamDao.update(team);
        Team updatedTeam = teamDao.findById(1L);
        assertEquals(updatedTeam.getTeamName(), "Updated Name");
    }
}
