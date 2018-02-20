package by.forecasts.repository;


import by.forecasts.entities.Forecast;
import by.forecasts.entities.Group;
import by.forecasts.entities.Match;
import by.forecasts.entities.MatchScore;
import by.forecasts.entities.PlayoffGroup;
import by.forecasts.entities.RegularGroup;
import by.forecasts.entities.Team;
import by.forecasts.entities.Tournament;
import by.forecasts.entities.User;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class BaseDaoTest extends BaseTest {

    @Test
    public void teamTest() {
        Team team = new Team("Ямайка");
        teamRepository.save(team);
        Iterable<Team> teams = teamRepository.findAll();
        assertEquals(teams.iterator().next().getTeamName(), "Ямайка");
    }

    @Test
    public void tournamentTest() {
        Team team = new Team("Ямайка");
        teamRepository.save(team);
        Tournament tournament = new Tournament("ЧМ 2018", team, LocalDate.now(), 2L);
        tournamentRepository.save(tournament);
        Iterable<Tournament> tournaments = tournamentRepository.findAll();
        assertEquals(tournaments.iterator().next().getName(), "ЧМ 2018");
    }

    @Test
    public void matchTest() {
        Team team1 = new Team("Ямайка");
        Team team2 = new Team("Иран");
        Team team3 = new Team("Россия");
        teamRepository.save(team1);
        teamRepository.save(team2);
        teamRepository.save(team3);
        Tournament tournament = new Tournament("ЧМ 2018", team3, LocalDate.now(), 1L);
        tournamentRepository.save(tournament);
        MatchScore matchScore = new MatchScore(1, 2);
        Match match1 = new Match(matchScore, LocalDateTime.now(), 1L, team1, team2, tournament);
        matchRepository.save(match1);

        Iterable<Match> matches = matchRepository.findAll();
        Match foundMatch = matches.iterator().next();

        assertThat(foundMatch.getFirstTeam().getTeamName(), equalTo("Ямайка"));
        assertThat(foundMatch.getTournament().getName(), equalTo("ЧМ 2018"));
        assertThat(foundMatch.getMatchFinalResult().getFirstResult(), equalTo(1));
        assertThat(foundMatch.getTournament().getOrganizer().getTeamName(), equalTo("Россия"));
    }

    @Test
    public void userTest() {
        User user = new User("Andrei", "Rizhnitsyn", "ra@bsb.by", 1L, "log", "pass");
        userRepository.save(user);
        Iterable<User> users = userRepository.findAll();
        assertEquals(users.iterator().next().getEmail(), "ra@bsb.by");
    }

    @Test
    public void forecastTest() {
        Team team1 = new Team("Ямайка");
        Team team2 = new Team("Иран");
        Team team3 = new Team("Россия");
        teamRepository.save(team1);
        teamRepository.save(team2);
        teamRepository.save(team3);
        Tournament tournament = new Tournament("ЧМ 2018", team3, LocalDate.now(), 1L);
        tournamentRepository.save(tournament);
        MatchScore matchScore = new MatchScore(1, 2);
        Match match = new Match(matchScore, LocalDateTime.now(), 1L, team1, team2, tournament);
        matchRepository.save(match);
        User user = new User("Andrei", "Rizhnitsyn", "ra@bsb.by", 1L, "log", "pass");
        userRepository.save(user);

        tournament.getUsers().add(user);

        MatchScore matchScore1 = new MatchScore(3, 2);
        Forecast forecast = new Forecast(matchScore1, user, match);
        forecastRepository.save(forecast);

        Iterable<Forecast> forecasts = forecastRepository.findAll();
        Forecast foundForecast = forecasts.iterator().next();

        assertThat(foundForecast.getMatchForecast().getFirstResult(), Matchers.equalTo(3));
        assertThat(foundForecast.getUser().getFirstName(), Matchers.equalTo("Andrei"));
    }

    @Test
    public void groupsTest() {
        Team team = new Team("Россия");
        teamRepository.save(team);
        Tournament tournament = new Tournament("ЧМ 2018", team, LocalDate.now(), 1L);
        tournamentRepository.save(tournament);

        RegularGroup regularGroup = new RegularGroup(4, 2, 2,
                1L, tournament);
        PlayoffGroup playoffGroup = new PlayoffGroup(2, 2L, tournament, true);
        groupRepository.save(playoffGroup);
        groupRepository.save(regularGroup);

        Team team1 = new Team("Германия");
        Team team2 = new Team("Испания");
        Team team3 = new Team("ЮАР");
        Team team4 = new Team("Япония");
        teamRepository.save(team1);
        teamRepository.save(team2);
        teamRepository.save(team3);
        teamRepository.save(team4);
        regularGroup.getTeamsInGroup().add(team1);
        regularGroup.getTeamsInGroup().add(team2);
        regularGroup.getTeamsInGroup().add(team3);
        regularGroup.getTeamsInGroup().add(team4);
        playoffGroup.getTeamsInGroup().add(team1);
        playoffGroup.getTeamsInGroup().add(team2);

        RegularGroup regularGroup1 = (RegularGroup) groupRepository.findOne(2L);
        PlayoffGroup playoffGroup1 = (PlayoffGroup) groupRepository.findOne(1L);
        Iterable<Group> groups = groupRepository.findAll();

        assertThat(regularGroup1.getTeamsCountInGroup(), Matchers.equalTo(4));
        assertThat(regularGroup1.getTournament().getName(), Matchers.equalTo("ЧМ 2018"));
        assertThat(playoffGroup1.isExtraTimeAllowed(), Matchers.is(true));
        assertThat(playoffGroup1.getTournament().getName(), Matchers.equalTo("ЧМ 2018"));
    }

    @Test
    public void getAllDaoTest() {
        Team team = new Team("Spain");
        Team team1 = new Team("France");
        teamRepository.save(team);
        teamRepository.save(team1);
        Iterator<Team> iterator = teamRepository.findAll().iterator();
        List<Team> teams = new ArrayList<>();

        while (iterator.hasNext()) {
            teams.add(iterator.next());
        }

        List<String> teamNames = teams.stream()
                .map(Team::getTeamName)
                .collect(Collectors.toList());
        assertThat(teamNames, contains("Spain", "France"));
    }

    @Test
    public void deleteDaoTest() {
        Team team = new Team("Spain");
        teamRepository.save(team);
        assertEquals(team.getTeamName(), "Spain");
        teamRepository.delete(team);

        Iterable<Team> teamIterable = teamRepository.findAll();

        assertThat(teamIterable.iterator().hasNext(), Matchers.is(false));
    }
}
