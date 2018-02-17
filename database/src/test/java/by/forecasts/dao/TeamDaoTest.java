package by.forecasts.dao;

import by.forecasts.entities.Team;
import by.forecasts.entities.Tournament;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class TeamDaoTest extends BaseTest {

    @Test
    public void getTeamOrganizerTest() {
        Team team = new Team("France");
        teamDao.save(team);
        Tournament tournament = new Tournament("Tournament", team, LocalDate.now(), 1L);
        tournamentDao.save(tournament);
        Team teamOrganizer = teamDao.getTeamOrganizer(tournament.getId());
        Assert.assertEquals(teamOrganizer.getTeamName(), "France");
    }
}
