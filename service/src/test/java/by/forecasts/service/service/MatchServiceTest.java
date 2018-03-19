package by.forecasts.service.service;

import by.forecasts.dto.MatchHardViewDto;
import by.forecasts.dto.MatchShortViewDto;
import by.forecasts.entities.Match;
import by.forecasts.entities.MatchScore;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class MatchServiceTest extends BaseServiceTest {

    @Test
    public void findMatchesByGroupIdTest() {
        List<Match> allByTournamentIdAndGroupId = matchService.findAllByTournamentIdAndGroupId(23L, 23L);
        System.out.println(allByTournamentIdAndGroupId);
    }

    @Test
    public void findAllByTournamentIdAndGroupIdTest() {
        List<Match> matches = matchService.findAllByTournamentIdAndGroupId(1L,48L);
        assertThat(matches.size(), Matchers.equalTo(1));
        assertEquals(matches.iterator().next().getSecondTeam().getTeamName(),"Франция");
    }

    @Test
    public void findByIdTest() {
        MatchHardViewDto match = matchService.findById(100L, 1L);
        assertEquals(match.getSecondTeam(),"Франция");
    }

    @Test
    public void addMatchScoreTest() {
        MatchHardViewDto byId = matchService.findById(100L, 1L);
        MatchScore matchScore = new MatchScore(2, 3);
        matchService.addMatchScore(100L, matchScore);
        Match one = matchService.findOne(100L);
        assertThat(byId.getMatchScore().getSecondResult(), Matchers.equalTo(0));
        assertThat(one.getMatchFinalResult().getSecondResult(), Matchers.equalTo(3));
    }

    @Test
    public void findAllByTournamentIdUserIdTest() {
        List<MatchShortViewDto> matches = matchService.findAllByTournamentIdUserId(1L, 1L);
        assertThat(matches.size(), Matchers.equalTo(51));
    }

    @Test
    public void findAllByTournamentIdUserIdPageableTest() {
        List<MatchShortViewDto> matches = matchService.findAllByTournamentIdUserIdPageable(1L, 1L, 1L);
        assertThat(matches.size(), Matchers.equalTo(10));
    }

    @Test
    public void calculateUserPointsPerMatchTest() {
        MatchScore matchScore1 = new MatchScore(2, 0);
        MatchScore matchScore2 = new MatchScore(2, 0);
        int i = matchService.calculateUserPointsPerMatch(matchScore1, matchScore2);
        assertEquals(i, 6);
    }

    @Test
    public void findMatchesOfSelectedTeamTest() {
        List<MatchShortViewDto> matches = matchService.findMatchesOfSelectedTeam(1L, 1L);
        assertThat(matches, Matchers.hasSize(7));
    }

//        assertThat(List<>(), hasItems("Россия", "Франция"));
    // assertThat(teams.size(), Matchers.greaterThan(30));
//        assertThat(tournaments, hasSize(2));
//        assertThat(foundMatch.getFirstTeam().getTeamName(), equalTo("Ямайка"));
//        assertEquals(matchesForForecast.iterator().next().getFirstTeam().getTeamName(), "Spain12");

}
