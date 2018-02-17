package by.forecasts.dao;


import by.forecasts.entities.Forecast;
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
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class BaseDaoTest extends BaseTest {

    @Test
    public void teamTest() {
        Team team = new Team("Ямайка");
        teamDao.save(team);
        List<Team> team1 = teamDao.findAll();
        System.out.println(team1);
        assertThat(team1, hasSize(1));
        assertEquals(team1.iterator().next().getTeamName(), "Ямайка");
    }

    @Test
    public void tournamentTest() {
        Team team = new Team("Ямайка");
        teamDao.save(team);
        Tournament tournament = new Tournament("ЧМ 2018", team, LocalDate.now(), 2L);
        tournamentDao.save(tournament);
        List<Tournament> tournament1 = tournamentDao.findAll();
        assertThat(tournament1, hasSize(1));
        assertEquals(tournament1.iterator().next().getName(), "ЧМ 2018");
    }

    @Test
    public void matchTest() {
        Team team1 = new Team("Ямайка");
        Team team2 = new Team("Иран");
        Team team3 = new Team("Россия");
        teamDao.save(team1);
        teamDao.save(team2);
        teamDao.save(team3);
        Tournament tournament = new Tournament("ЧМ 2018", team3, LocalDate.now(), 1L);
        tournamentDao.save(tournament);
        MatchScore matchScore = new MatchScore(1, 2);
        Match match1 = new Match(matchScore, LocalDateTime.now(), 1L, team1, team2, tournament);
        matchDao.save(match1);

        List<Match> match = matchDao.findAll();

        assertThat(match, hasSize(1));
        Match foundMatch = match.iterator().next();

        assertThat(foundMatch.getFirstTeam().getTeamName(), Matchers.equalTo("Ямайка"));
        assertThat(foundMatch.getTournament().getName(), Matchers.equalTo("ЧМ 2018"));
        assertThat(foundMatch.getMatchFinalResult().getFirstResult(), Matchers.equalTo(1));
        assertThat(foundMatch.getTournament().getOrganizer().getTeamName(), Matchers.equalTo("Россия"));
    }

    @Test
    public void userTest() {
        User user = new User("Andrei", "Rizhnitsyn", "ra@bsb.by", 1L, "log", "pass");
        userDao.save(user);

        List<User> user1 = userDao.findAll();

        assertThat(user1, hasSize(1));
        assertEquals(user1.iterator().next().getEmail(), "ra@bsb.by");
    }

    @Test
    public void forecastTest() {
        Team team1 = new Team("Ямайка");
        Team team2 = new Team("Иран");
        Team team3 = new Team("Россия");
        teamDao.save(team1);
        teamDao.save(team2);
        teamDao.save(team3);
        Tournament tournament = new Tournament("ЧМ 2018", team3, LocalDate.now(), 1L);
        tournamentDao.save(tournament);
        MatchScore matchScore = new MatchScore(1, 2);
        Match match = new Match(matchScore, LocalDateTime.now(), 1L, team1, team2, tournament);
        matchDao.save(match);
        User user = new User("Andrei", "Rizhnitsyn", "ra@bsb.by", 1L, "log", "pass");
        userDao.save(user);
        tournament.getUsers().add(user);
        MatchScore matchScore1 = new MatchScore(3, 2);
        Forecast forecast = new Forecast(matchScore1, user, match);
        forecastDao.save(forecast);

        List<Forecast> forecast1 = forecastDao.findAll();
        assertThat(forecast1, hasSize(1));
        Forecast foundForecast = forecast1.iterator().next();

        assertThat(foundForecast.getMatchForecast().getFirstResult(), Matchers.equalTo(3));
        assertThat(foundForecast.getUser().getFirstName(), Matchers.equalTo("Andrei"));
    }

    @Test
    public void groupsTest() {
        Team team = new Team("Россия");
        teamDao.save(team);
        Tournament tournament = new Tournament("ЧМ 2018", team, LocalDate.now(), 1L);
        tournamentDao.save(tournament);

        RegularGroup regularGroup = new RegularGroup(4, 2, 2,
                1L, tournament);
        PlayoffGroup playoffGroup = new PlayoffGroup(2, 2L, tournament, true);
        groupDao.save(playoffGroup);
        groupDao.save(regularGroup);

        Team team1 = new Team("Германия");
        Team team2 = new Team("Испания");
        Team team3 = new Team("ЮАР");
        Team team4 = new Team("Япония");
        teamDao.save(team1);
        teamDao.save(team2);
        teamDao.save(team3);
        teamDao.save(team4);
        regularGroup.getTeamsInGroup().add(team1);
        regularGroup.getTeamsInGroup().add(team2);
        regularGroup.getTeamsInGroup().add(team3);
        regularGroup.getTeamsInGroup().add(team4);
        playoffGroup.getTeamsInGroup().add(team1);
        playoffGroup.getTeamsInGroup().add(team2);

        RegularGroup regularGroup1 = (RegularGroup) groupDao.findById(2L);
        PlayoffGroup playoffGroup1 = (PlayoffGroup) groupDao.findById(1L);

        assertThat(regularGroup1.getTeamsCountInGroup(), Matchers.equalTo(4));
        assertThat(regularGroup1.getTournament().getName(), Matchers.equalTo("ЧМ 2018"));
        assertThat(playoffGroup1.isExtraTimeAllowed(), Matchers.is(true));
        assertThat(playoffGroup1.getTournament().getName(), Matchers.equalTo("ЧМ 2018"));
    }

    @Test
    public void getAllDaoTest() {
        Team team = new Team("Spain");
        Team team1 = new Team("France");
        teamDao.save(team);
        teamDao.save(team1);
        List<Team> teamList = teamDao.findAll();
        assertThat(teamList, hasSize(2));
        List<String> teamNames = teamList.stream()
                .map(Team::getTeamName)
                .collect(Collectors.toList());
        assertThat(teamNames, contains("Spain", "France"));
    }

    @Test
    public void deleteDaoTest() {
        Team team = new Team("Spain");
        teamDao.save(team);
        assertEquals(team.getTeamName(), "Spain");
        teamDao.delete(team);
        List<Team> teamList = teamDao.findAll();
        assertThat(teamList, hasSize(0));
    }

    @Test
    public void updateDaoTest() {
        Team team = new Team("Spain");
        teamDao.save(team);
        assertEquals(team.getTeamName(), "Spain");
        team.setTeamName("Updated Name");
        teamDao.update(team);
        List<Team> updatedTeam = teamDao.findAll();
        assertThat(updatedTeam, hasSize(1));
        Team team1 = updatedTeam.iterator().next();
        assertEquals(team1.getTeamName(), "Updated Name");
    }
}
