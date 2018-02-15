package by.forecasts.dao;

import by.forecasts.dao.common.BaseDaoImpl;
import by.forecasts.entities.Match;
import by.forecasts.utils.SessionManager;
import org.hibernate.Session;

import java.util.List;

public final class MatchDaoImpl extends BaseDaoImpl<Match> {

    private static MatchDaoImpl instance;

    private MatchDaoImpl() {
        super(Match.class);
    }

    public static MatchDaoImpl getInstance() {
        if (instance == null) {
            synchronized (MatchDaoImpl.class) {
                if (instance == null) {
                    instance = new MatchDaoImpl();
                }
            }
        }
        return instance;
    }

    public List<Match> getMatchesForForecast(Long tournamentId, Long userId) {
        Session session = SessionManager.getSession();
        session.beginTransaction();

        List<Match> matches = session.createQuery("select m from Match m where m.tournament.id = :trId "
                + "and m.id not in (select f from Forecast f where f.user.id = :userId)", Match.class)
                .setParameter("trId", tournamentId)
                .setParameter("userId", userId)
                .getResultList();

        session.getTransaction().commit();
        session.close();

        return matches;
    }
}
