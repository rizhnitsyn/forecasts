package by.forecasts.dao;

import by.forecasts.entities.Tournament;
import by.forecasts.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public final class TournamentDao extends BaseDao<Tournament> {

    private static TournamentDao INSTANCE;
    private SessionFactory SESSION_FACTORY = new Configuration().configure("hibernate_h2.cfg.xml").buildSessionFactory();
//    private SessionFactory SESSION_FACTORY = new Configuration().configure("hibernate_mysql.cfg.xml").buildSessionFactory();

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

        User user = session.createQuery("select u from User u where u.id = :userId", User.class)
                .setParameter("userId", userId)
                .getSingleResult();
        List<Tournament> tournaments = new ArrayList<>(user.getTournaments());

        session.getTransaction().commit();
        session.close();

        return tournaments;
    }
}
