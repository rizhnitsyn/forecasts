package by.forecasts.dao;

import by.forecasts.dao.common.BaseDaoImpl;
import by.forecasts.entities.Tournament;
import by.forecasts.entities.User;
import by.forecasts.utils.SessionManager;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public final class TournamentDaoImpl extends BaseDaoImpl<Tournament> {

    private static TournamentDaoImpl instance;

    private TournamentDaoImpl() {
        super(Tournament.class);
    }

    public static TournamentDaoImpl getInstance() {
        if (instance == null) {
            synchronized (TournamentDaoImpl.class) {
                if (instance == null) {
                    instance = new TournamentDaoImpl();
                }
            }
        }
        return instance;
    }

    public List<Tournament> getTournamentsFilterByUser(Long userId) {
        Session session = SessionManager.getSession();
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
