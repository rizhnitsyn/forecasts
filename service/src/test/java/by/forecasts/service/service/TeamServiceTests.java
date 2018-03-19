package by.forecasts.service.service;

import by.forecasts.entities.Team;
import org.junit.Test;

import java.util.List;
import java.util.Set;

public class TeamServiceTests extends BaseServiceTest  {

    @Test
    public void teamGroupTests() {
        List<Team> allByGroupsId = teamService.findAllRegularTeamsNotInUseInTournament(1L);
        System.out.println(allByGroupsId);
        Set<Team> allByGroupsId2 = teamService.findAllPlayoffTeamsNotInUseInTournament(1L);
        System.out.println(allByGroupsId2);
    }
}
