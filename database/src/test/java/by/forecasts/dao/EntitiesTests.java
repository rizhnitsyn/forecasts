package by.forecasts.dao;

import by.forecasts.Entities.Match;
import by.forecasts.Entities.MatchScore;
import by.forecasts.Entities.Team;
import by.forecasts.Entities.Tournament;
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
    public void TeamTest() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Team team = new Team("Ямайка");
        session.save(team);
        session.getTransaction().commit();
        session.close();

        Session session2 = sessionFactory.openSession();
        session2.beginTransaction();
        Team team1 = session2.get(Team.class, 1L);
        Assert.assertThat(team1.getTeamName(), Matchers.equalTo("Ямайка"));
        session2.getTransaction().commit();
        session2.close();

        System.out.println(team1);
    }


    @Test
    public void TournamentTest() {
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


}
