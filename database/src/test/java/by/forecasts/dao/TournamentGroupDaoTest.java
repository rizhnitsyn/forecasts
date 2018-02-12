package by.forecasts.dao;

import by.forecasts.entities.*;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TournamentGroupDaoTest extends BaseTest {

    @Test
    public void getListOfGroupsOfTournamentTest() {
        Team team1 = new Team("France");
        TEAM_DAO.save(team1);

        Tournament tournament1 = new Tournament("Tournament 1", team1, LocalDate.now(), 1L);
        TOURNAMENT_DAO.save(tournament1);

        RegularGroup regularGroup1 = new RegularGroup(4, 2, 2, 1L, tournament1);
        RegularGroup regularGroup2 = new RegularGroup(4, 2, 2, 2L, tournament1);
        TOURNAMENT_GROUP_DAO.save(regularGroup1);
        TOURNAMENT_GROUP_DAO.save(regularGroup2);
        List<RegularGroup> groupsOfTournament = TOURNAMENT_GROUP_DAO.getGroupsOfTournament(1L);

        assertThat(groupsOfTournament, hasSize(2));
        assertEquals(groupsOfTournament.iterator().next().getGroupOutCount(), 2);
    }
}
