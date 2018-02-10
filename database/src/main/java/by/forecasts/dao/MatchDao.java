package by.forecasts.dao;

import by.forecasts.entities.Match;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class MatchDao extends BaseDao<Match> {

    private static MatchDao INSTANCE;
    private SessionFactory SESSION_FACTORY = new Configuration().configure("hibernate_h2.cfg.xml").buildSessionFactory();


    private MatchDao() {
        super(Match.class);
    }

    public static MatchDao getInstance() {
        if (INSTANCE == null) {
            synchronized (MatchDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MatchDao();
                }
            }
        }
        return INSTANCE;
    }

    public List<Match> getMatchesForForecast(Long tournamentId, Long userId) {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();

        List<Match> matches = session.createQuery("select m from Match m where m.tournament.id = :trId " +
                "and m.id not in (select f from Forecast f where f.user.id = :userId)", Match.class)
                .setParameter("trId", tournamentId)
                .setParameter("userId", userId)
                .getResultList();

        session.getTransaction().commit();
        session.close();

        return matches;
    }
}
