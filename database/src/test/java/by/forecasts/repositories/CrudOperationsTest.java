package by.forecasts.repositories;


import by.forecasts.entities.Forecast;
import by.forecasts.entities.Match;
import by.forecasts.entities.MatchScore;
import by.forecasts.entities.MatchState;
import by.forecasts.entities.PlayoffGroup;
import by.forecasts.entities.RegularGroup;
import by.forecasts.entities.Team;
import by.forecasts.entities.Tournament;
import by.forecasts.entities.TournamentState;
import by.forecasts.entities.User;
import by.forecasts.entities.UserState;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class CrudOperationsTest extends BaseTest {

    @Test
    public void teamTest() {
        Team team = new Team("Ямайка");
        teamRepository.save(team);
        List<Team> teams = teamRepository.findAll();

        assertThat(teams, hasSize(1));
        assertEquals(teams.iterator().next().getTeamName(), "Ямайка");
    }

    @Test
    public void tournamentTest() {
        Team team = new Team("Ямайка");
        teamRepository.save(team);
        TournamentState tournamentState = new TournamentState("active");
        tournamentStateRepository.save(tournamentState);
        Tournament tournament = new Tournament("ЧМ 2018", team, LocalDate.now(), tournamentState);
        tournamentRepository.save(tournament);
        List<Tournament> tournaments = tournamentRepository.findAll();

        assertThat(tournaments, hasSize(1));
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
        TournamentState tournamentState = new TournamentState("active");
        tournamentStateRepository.save(tournamentState);
        Tournament tournament = new Tournament("ЧМ 2018", team3, LocalDate.now(), tournamentState);
        tournamentRepository.save(tournament);
        MatchScore matchScore = new MatchScore(1, 2);
        MatchState matchState = new MatchState("active match");
        matchStateRepository.save(matchState);
        RegularGroup regularGroup = new RegularGroup(4, 2, 2,
                "group 2", tournament);
        regularGroupRepository.save(regularGroup);

        Match match1 = new Match(matchScore, LocalDateTime.now(), matchState, team1, team2, tournament);
        match1.setGroup(regularGroup);
        matchRepository.save(match1);

        List<Match> matches = matchRepository.findAll();
        Match foundMatch = matches.iterator().next();

        assertThat(matches, hasSize(1));
        assertThat(foundMatch.getFirstTeam().getTeamName(), equalTo("Ямайка"));
        assertThat(foundMatch.getTournament().getName(), equalTo("ЧМ 2018"));
        assertThat(foundMatch.getMatchFinalResult().getFirstResult(), equalTo(1));
        assertThat(foundMatch.getTournament().getOrganizer().getTeamName(), equalTo("Россия"));
    }

    @Test
    public void userTest() {
        UserState userState = new UserState("active user");
        userStateRepository.save(userState);
        User user = new User("Andrei", "Rizhnitsyn", "ra@bsb.by", userState, "log", "pass");
        userRepository.save(user);
        List<User> users = userRepository.findAll();

        assertThat(users, hasSize(1));
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
        TournamentState tournamentState = new TournamentState("active");
        tournamentStateRepository.save(tournamentState);
        Tournament tournament = new Tournament("ЧМ 2018", team3, LocalDate.now(), tournamentState);
        tournamentRepository.save(tournament);
        MatchScore matchScore = new MatchScore(1, 2);
        MatchState matchState = new MatchState("active match");
        matchStateRepository.save(matchState);
        RegularGroup regularGroup = new RegularGroup(4, 2, 2,
                "group 2", tournament);
        regularGroupRepository.save(regularGroup);
        Match match = new Match(matchScore, LocalDateTime.now(), matchState, team1, team2, tournament);
        match.setGroup(regularGroup);
        matchRepository.save(match);
        UserState userState = new UserState("active user");
        userStateRepository.save(userState);
        User user = new User("Andrei", "Rizhnitsyn", "ra@bsb.by", userState, "log", "pass");
        userRepository.save(user);

        tournament.getUsers().add(user);

        MatchScore matchScore1 = new MatchScore(3, 2);
        Forecast forecast = new Forecast(matchScore1, user, match);
        forecastRepository.save(forecast);

        List<Forecast> forecasts = forecastRepository.findAll();
        Forecast foundForecast = forecasts.iterator().next();

        assertThat(forecasts, hasSize(1));
        assertThat(foundForecast.getMatchForecast().getFirstResult(), Matchers.equalTo(3));
        assertThat(foundForecast.getUser().getFirstName(), Matchers.equalTo("Andrei"));
    }

    @Test
    public void groupsTest() {
        Team team = new Team("Россия");
        teamRepository.save(team);
        TournamentState tournamentState = new TournamentState("active");
        tournamentStateRepository.save(tournamentState);
        Tournament tournament = new Tournament("ЧМ 2018", team, LocalDate.now(), tournamentState);
        tournamentRepository.save(tournament);

        RegularGroup regularGroup = new RegularGroup(4, 2, 2,
                "group 2", tournament);
        PlayoffGroup playoffGroup = new PlayoffGroup(2, "group 3", tournament, true);
        PlayoffGroup pGroup = groupRepository.save(playoffGroup);
        RegularGroup rGroup = groupRepository.save(regularGroup);

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

        RegularGroup regularGroup1 = (RegularGroup) groupRepository.findOne(rGroup.getId());
        PlayoffGroup playoffGroup1 = (PlayoffGroup) groupRepository.findOne(pGroup.getId());

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
        List<Team> teams = teamRepository.findAll();

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

        List<Team> teamIterable = teamRepository.findAll();

        assertThat(teamIterable.iterator().hasNext(), Matchers.is(false));
    }
}
