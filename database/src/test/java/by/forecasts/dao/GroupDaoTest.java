package by.forecasts.dao;

import by.forecasts.entities.*;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class GroupDaoTest extends BaseTest {

    @Test
    public void getListOfGroupsOfTournamentTest() {
        Team team1 = new Team("France");
        teamDao.save(team1);

        Tournament tournament1 = new Tournament("Tournament 1", team1, LocalDate.now(), 1L);
        tournamentDao.save(tournament1);

        RegularGroup regularGroup1 = new RegularGroup(4, 2, 2, 1L, tournament1);
        RegularGroup regularGroup2 = new RegularGroup(4, 2, 2, 2L, tournament1);
        tournamentGroupDao.save(regularGroup1);
        tournamentGroupDao.save(regularGroup2);
        List<RegularGroup> groupsOfTournament = tournamentGroupDao.getGroupsOfTournament(1L);

        assertThat(groupsOfTournament, hasSize(2));
        assertEquals(groupsOfTournament.iterator().next().getGroupOutCount(), 2);
    }
}
