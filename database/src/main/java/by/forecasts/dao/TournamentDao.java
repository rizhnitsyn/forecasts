package by.forecasts.dao;

import by.forecasts.entities.Tournament;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public final class TournamentDao extends BaseDao<Tournament> {

    private static TournamentDao INSTANCE;
    private SessionFactory SESSION_FACTORY = new Configuration().configure("hibernate_h2.cfg.xml").buildSessionFactory();

    private TournamentDao() {
        super(Tournament.class);
    }

    public static TournamentDao getInstance() {
        if (INSTANCE == null) {
            synchronized (TournamentDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TournamentDao();
                }
            }
        }
        return INSTANCE;
    }

    public List<Tournament> getTournamentsFilterByUser(Long userId) {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();

        List<Tournament> tournamentList = session.createQuery("select u.tournaments from User u where u.id = :userId", Tournament.class)
                .setParameter("userId", userId)
                .getResultList();

        session.getTransaction().commit();
        session.close();

        return tournamentList;
    }
}
