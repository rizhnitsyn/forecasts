package by.forecasts.service.service;

import by.forecasts.entities.Group;
import by.forecasts.entities.Team;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Set;

public class GroupServiceTest extends BaseServiceTest {

    @Test
    public void getAllGroupsByTournamentIDTest() {
        List<Group> groups = groupService.getAllGroupsByTournamentID(1L);
        Assert.assertThat(groups.size(), Matchers.greaterThan(5));
    }

    @Test
    public void findOneTest() {
        Group group = groupService.findOne(29L);
        Assert.assertEquals(group.getGroupName(), "Группа D");
    }

    @Test
    public void addTeamTest() {
        groupService.addTeam(30L, 1L);
        Team team = teamService.findOne(1L);
        Group group = groupService.findOne(30L);
        Set<Team> teamsInGroup = group.getTeamsInGroup();
        Assert.assertThat(teamsInGroup, Matchers.hasItem(team));
    }

    @Test
    public void delTeamTest() {
        groupService.delTeam(29L, 1L);
        Team team = teamService.findOne(1L);
        Group group = groupService.findOne(29L);
        Set<Team> teamsInGroup = group.getTeamsInGroup();
        Assert.assertThat(teamsInGroup, Matchers.not(Matchers.hasItem(team)));
    }
}
