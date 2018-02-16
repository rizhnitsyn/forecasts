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

        RegularGroup regularGroup = new RegularGroup(4, 2, 2, 1L, tournament1);
        PlayoffGroup playoffGroup = new PlayoffGroup(4, 2L, tournament1,  true);
        regularGroupDao.save(regularGroup);
        playoffGroupDao.save(playoffGroup);
        List<Group> groupsOfTournament = groupDao.getGroupsOfTournament(1L);
        List<RegularGroup> regularGroups = regularGroupDao.getGroupsOfTournament(1L);
        List<PlayoffGroup> playoffGroups = playoffGroupDao.getGroupsOfTournament(1L);

        assertThat(groupsOfTournament, hasSize(2));
        assertThat(regularGroups, hasSize(1));
        assertThat(playoffGroups, hasSize(1));
    }
}
