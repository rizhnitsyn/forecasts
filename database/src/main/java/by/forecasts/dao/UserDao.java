package by.forecasts.dao;

import by.forecasts.entities.Tournament;
import by.forecasts.entities.User;
import by.forecasts.utils.SessionManager;
import org.hibernate.Session;

public final class UserDao extends BaseDao<User> {

    private static UserDao instance;

    private UserDao() {
        super(User.class);
    }

    public static UserDao getInstance() {
        if (instance == null) {
            synchronized (MatchDao.class) {
                if (instance == null) {
                    instance = new UserDao();
                }
            }
        }
        return instance;
    }

    public void registerOnTournament(Tournament tournament, User user) {
        Session session = SessionManager.getSession();
        session.beginTransaction();

        session.refresh(tournament);
        tournament.getUsers().add(user);
        user.getTournaments().add(tournament);

        session.getTransaction().commit();
        session.close();
    }
}
