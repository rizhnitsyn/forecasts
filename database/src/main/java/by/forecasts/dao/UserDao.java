package by.forecasts.dao;

import by.forecasts.entities.Tournament;
import by.forecasts.entities.User;
import by.forecasts.utils.SessionManager;
import org.hibernate.Session;

public class UserDao extends BaseDao<User> {

    private static UserDao INSTANCE;

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
        Session session = SessionManager.getSession();
        session.beginTransaction();

        session.refresh(tournament);
        tournament.getUsers().add(user);
        user.getTournaments().add(tournament);

        session.getTransaction().commit();
        session.close();
    }
}
