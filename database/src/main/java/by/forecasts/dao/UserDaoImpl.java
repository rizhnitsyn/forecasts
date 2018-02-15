package by.forecasts.dao;

import by.forecasts.dao.common.BaseDaoImpl;
import by.forecasts.entities.Tournament;
import by.forecasts.entities.User;
import by.forecasts.utils.SessionManager;
import org.hibernate.Session;

public final class UserDaoImpl extends BaseDaoImpl<User> {

    private static UserDaoImpl instance;

    private UserDaoImpl() {
        super(User.class);
    }

    public static UserDaoImpl getInstance() {
        if (instance == null) {
            synchronized (MatchDaoImpl.class) {
                if (instance == null) {
                    instance = new UserDaoImpl();
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
