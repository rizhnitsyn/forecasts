package by.forecasts.dao;

import by.forecasts.entities.Tournament;
import by.forecasts.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UserDao extends BaseDao<User> {

    private static UserDao INSTANCE;
    private SessionFactory SESSION_FACTORY = new Configuration().configure("hibernate_h2.cfg.xml").buildSessionFactory();
//    private SessionFactory SESSION_FACTORY = new Configuration().configure("hibernate_mysql.cfg.xml").buildSessionFactory();

    private UserDao() {
        super(User.class);
    }

    public static UserDao getInstance() {
        if (INSTANCE == null) {
            synchronized (MatchDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UserDao();
                }
            }
        }
        return INSTANCE;
    }

    public void registerOnTournament(Tournament tournament, User user) {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();

        session.refresh(tournament);
        tournament.getUsers().add(user);
        user.getTournaments().add(tournament);

        session.getTransaction().commit();
        session.close();
    }
}
