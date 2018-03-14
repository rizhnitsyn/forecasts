package by.forecasts.service;

import by.forecasts.entities.Team;
import org.junit.Test;

import java.util.List;

public class TeamServiceTests extends BaseServiceTest  {

    @Test
    public void teamGroupTests() {
        List<Team> allByGroupsId = teamService.findAllRegularTeamsNotInUseInTournament(1L);
        System.out.println(allByGroupsId);
        List<Team> allByGroupsId2 = teamService.findAllPlayoffTeamsNotInUseInTournament(1L);
        System.out.println(allByGroupsId2);
    }
}
