package by.forecasts.entities;

import by.forecasts.entities.*;
import org.hamcrest.Matchers;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EntitiesTests {

    private SessionFactory sessionFactory = new Configuration().configure("hibernate_h2.cfg.xml").buildSessionFactory();

    @Test
    public void teamTest() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Team team = new Team("Ямайка");
        session.save(team);

        Team team2 = new Team("Иран");
        Team team3 = new Team("Россия");
        session.save(team2);
        session.save(team3);
        Tournament tournament = new Tournament("ЧМ 2018", team, LocalDate.now(), 1L);
        session.save(tournament);
        MatchScore matchScore = new MatchScore(1, 2);
        Match match = new Match(matchScore, LocalDateTime.now(), 1L, team2, team3, tournament);
        session.save(match);

        session.getTransaction().commit();
        session.close();

        Session session2 = sessionFactory.openSession();
        session2.beginTransaction();
        Team team1 = session2.get(Team.class, 1L);
        Team team4 = session2.get(Team.class, 2L);
        Assert.assertThat(team1.getTeamName(), Matchers.equalTo("Ямайка"));
        Assert.assertThat(team4.getHomeMatches(), Matchers.hasSize(1));
        Assert.assertThat(team4.getHomeMatches().iterator().next().getTournament().getName(),
                Matchers.equalTo("ЧМ 2018"));

        session2.getTransaction().commit();
        session2.close();
    }

    @Test
    public void tournamentTest() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Team team = new Team("Ямайка");
        session.save(team);
        Tournament tournament = new Tournament("ЧМ 2018", team, LocalDate.now(), 2L);
        session.save(tournament);
        session.getTransaction().commit();
        session.close();

        Session session2 = sessionFactory.openSession();
        session2.beginTransaction();
        Tournament tournament1 = session2.get(Tournament.class, 1L);
        Assert.assertThat(tournament1.getName(), Matchers.equalTo("ЧМ 2018"));
        session2.getTransaction().commit();
        session2.close();

        System.out.println(tournament1);
    }

    @Test
    public void matchTest() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Team team1 = new Team("Ямайка");
        Team team2 = new Team("Иран");
        Team team3 = new Team("Россия");
        session.save(team1);
        session.save(team2);
        session.save(team3);
        Tournament tournament = new Tournament("ЧМ 2018", team3, LocalDate.now(), 1L);
        session.save(tournament);
        MatchScore matchScore = new MatchScore(1, 2);
        Match match1 = new Match(matchScore, LocalDateTime.now(), 1L, team1, team2, tournament);
        session.save(match1);
        session.getTransaction().commit();
        session.close();

        Session session2 = sessionFactory.openSession();
        session2.beginTransaction();
        Match match = session2.get(Match.class, 1L);

        Assert.assertThat(match.getFirstTeam().getTeamName(), Matchers.equalTo("Ямайка"));
        Assert.assertThat(match.getTournament().getName(), Matchers.equalTo("ЧМ 2018"));
        Assert.assertThat(match.getMatchFinalResult().getFirstResult(), Matchers.equalTo(1));
        Assert.assertThat(match.getTournament().getOrganizer().getTeamName(), Matchers.equalTo("Россия"));
        session2.getTransaction().commit();
        session2.close();

        System.out.println(match);
    }

    @Test
    public void userTest() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Team team3 = new Team("Россия");
        session.save(team3);
        Tournament tournament = new Tournament("ЧМ 2018", team3, LocalDate.now(), 1L);
        session.save(tournament);
        User user = new User("Andrei", "Rizhnitsyn", "ra@bsb.by", 1L, "log", "pass");
        session.save(user);
        tournament.getUsers().add(user);
        session.getTransaction().commit();
        session.close();

        Session session2 = sessionFactory.openSession();
        session2.beginTransaction();
        User user1 = session2.get(User.class, 1L);
        Assert.assertThat(user1.getEmail(), Matchers.equalTo("ra@bsb.by"));
        Assert.assertThat(user1.getTournaments().iterator().next().getName(), Matchers.equalTo("ЧМ 2018"));
        System.out.println(user1);
        session2.getTransaction().commit();
        session2.close();
    }

    @Test
    public void forecastTest() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Team team1 = new Team("Ямайка");
        Team team2 = new Team("Иран");
        Team team3 = new Team("Россия");
        session.save(team1);
        session.save(team2);
        session.save(team3);
        Tournament tournament = new Tournament("ЧМ 2018", team3, LocalDate.now(), 1L);
        session.save(tournament);
        MatchScore matchScore = new MatchScore(1, 2);
        Match match = new Match(matchScore, LocalDateTime.now(), 1L, team1, team2, tournament);
        session.save(match);
        User user = new User("Andrei", "Rizhnitsyn", "ra@bsb.by", 1L, "log", "pass");
        session.save(user);
        tournament.getUsers().add(user);
        MatchScore matchScore1 = new MatchScore(3, 2);
        Forecast forecast = new Forecast(matchScore1, user, match);
        session.save(forecast);
        session.getTransaction().commit();
        session.close();

        Session session2 = sessionFactory.openSession();
        session2.beginTransaction();
        Forecast forecast1 = session2.get(Forecast.class, 1L);
        Assert.assertThat(forecast1.getMatchForecast().getFirstResult(), Matchers.equalTo(3));
        Assert.assertThat(forecast1.getUser().getFirstName(), Matchers.equalTo("Andrei"));
        System.out.println(forecast1);
        session2.getTransaction().commit();
        session2.close();
    }

    @Test
    public void groupsTest() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Team team = new Team("Россия");
        session.save(team);
        Tournament tournament = new Tournament("ЧМ 2018", team, LocalDate.now(), 1L);
        session.save(tournament);

        RegularGroup regularGroup = new RegularGroup(4, 2, 2,
                1L, tournament);
        PlayoffGroup playoffGroup = new PlayoffGroup(2, 2L, tournament, true);
        session.save(playoffGroup);
        session.save(regularGroup);

        Team team1 = new Team("Германия");
        Team team2 = new Team("Испания");
        Team team3 = new Team("ЮАР");
        Team team4 = new Team("Япония");
        session.save(team1);
        session.save(team2);
        session.save(team3);
        session.save(team4);
        regularGroup.getTeamsInGroup().add(team1);
        regularGroup.getTeamsInGroup().add(team2);
        regularGroup.getTeamsInGroup().add(team3);
        regularGroup.getTeamsInGroup().add(team4);
        playoffGroup.getTeamsInGroup().add(team1);
        playoffGroup.getTeamsInGroup().add(team2);

        session.getTransaction().commit();
        session.close();

        Session session2 = sessionFactory.openSession();
        session2.beginTransaction();
        RegularGroup regularGroup1 = session2.get(RegularGroup.class, 2L);
        PlayoffGroup playoffGroup1 = session2.get(PlayoffGroup.class, 1L);

        Assert.assertThat(regularGroup1.getTeamsCountInGroup(), Matchers.equalTo(4));
        Assert.assertThat(regularGroup1.getTournament().getName(), Matchers.equalTo("ЧМ 2018"));
        Assert.assertThat(playoffGroup1.isExtraTimeAllowed(), Matchers.is(true));
        Assert.assertThat(playoffGroup1.getTournament().getName(), Matchers.equalTo("ЧМ 2018"));

        System.out.println(regularGroup1);
        System.out.println(playoffGroup1);
        session2.getTransaction().commit();
        session2.close();
    }


}
