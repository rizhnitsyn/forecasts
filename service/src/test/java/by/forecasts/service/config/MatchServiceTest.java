package by.forecasts.service.config;

import by.forecasts.entities.Match;
import by.forecasts.service.BaseServiceTest;
import org.junit.Test;

import java.util.List;

public class MatchServiceTest extends BaseServiceTest {

    @Test
    public void findMatchesByGroupIdTest() {
        List<Match> allByTournamentIdAndGroupId = matchService.findAllByTournamentIdAndGroupId(23L, 23L);
        System.out.println(allByTournamentIdAndGroupId);
    }
}
