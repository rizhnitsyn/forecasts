package by.forecasts.dao;

import by.forecasts.entities.Team;
import by.forecasts.entities.Tournament;
import by.forecasts.utils.SessionManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class TeamDaoTest extends BaseTest {

    @Test
    public void getTeamOrganizerTest() {
        Team team = new Team("France");
        TEAM_DAO.save(team);
        Tournament tournament = new Tournament("Tournament", team, LocalDate.now(), 1L);
        TOURNAMENT_DAO.save(tournament);
        Team teamOrganizer = TEAM_DAO.getTeamOrganizer(tournament.getId());
        Assert.assertEquals(teamOrganizer.getTeamName(), "France");
    }
}
