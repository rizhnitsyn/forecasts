package by.forecasts.dao;

import by.forecasts.entities.Tournament;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TournamentDao {

    private static TournamentDao INSTANCE;
    private SessionFactory sessionFactory = new Configuration().configure("hibernate_mysql.cfg.xml").buildSessionFactory();

    private TournamentDao() {}

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

    public Tournament getTournamentById(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Tournament tournament = session.get(Tournament.class, id);
        session.getTransaction().commit();
        session.close();
        return tournament;
    }
}
