package by.forecasts.repositories;

import by.forecasts.entities.Group;
import by.forecasts.entities.Match;
import by.forecasts.entities.Team;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Set;

public class TeamTest extends BaseTest {

    @Test
    public void teamTest() {
        Team team = new Team();
        team.setTeamName("BY");
        teamRepository.save(team);
        Set<Group> groups = team.getGroups();
        Set<Match> homeMatches = team.getHomeMatches();
        Set<Match> visitorMatches = team.getVisitorMatches();
        Assert.assertEquals(groups.size(), 0);
        Assert.assertEquals(homeMatches.size(), 0);
        Assert.assertEquals(visitorMatches.size(), 0);
    }


}
