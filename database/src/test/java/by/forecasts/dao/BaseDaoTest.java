package by.forecasts.dao;

import by.forecasts.dao.TeamDao;
import by.forecasts.dao.TournamentDao;
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

public class BaseDaoTest {

    private static final TeamDao TEAM_DAO = TeamDao.getInstance();
    private static final TournamentDao TOURNAMENT_DAO = TournamentDao.getInstance();

    @Test
    public void saveDaoTest() {
        Team team = new Team("Poland");
        Tournament tournament = new Tournament("Tournament", team, LocalDate.now(), 1L);
        TEAM_DAO.save(team);
        TOURNAMENT_DAO.save(tournament);
        assertEquals(team.getTeamName(), "Poland");
        assertEquals(tournament.getOrganizer().getTeamName(), "Poland");
    }

    @Test
    public void getByIdDaoTest() {
        Team team = new Team("Spain");
        TEAM_DAO.save(team);
        Team foundTeam = TEAM_DAO.findById(1L);
        assertEquals(foundTeam.getTeamName(), "Spain");
    }

    @Test
    public void getAllDaoTest() {
        Team team = new Team("Spain");
        Team team1 = new Team("France");
        TEAM_DAO.save(team);
        TEAM_DAO.save(team1);
        List<Team> teamList = TEAM_DAO.findAll();
        assertThat(teamList, hasSize(2));
        List<String> teamNames = teamList.stream()
                .map(Team::getTeamName)
                .collect(Collectors.toList());
        assertThat(teamNames, contains("Spain", "France"));
    }

    @Test
    public void deleteDaoTest() {
        Team team = new Team("Spain");
        TEAM_DAO.save(team);
        assertEquals(team.getTeamName(), "Spain");
        TEAM_DAO.delete(team);
        List<Team> teamList = TEAM_DAO.findAll();
        assertThat(teamList, hasSize(0));
    }

    @Test
    public void updateDaoTest() {
        Team team = new Team("Spain");
        TEAM_DAO.save(team);
        assertEquals(team.getTeamName(), "Spain");
        team.setTeamName("Updated Name");
        TEAM_DAO.update(team);
        Team updatedTeam = TEAM_DAO.findById(1L);
        assertEquals(updatedTeam.getTeamName(), "Updated Name");
    }
}
