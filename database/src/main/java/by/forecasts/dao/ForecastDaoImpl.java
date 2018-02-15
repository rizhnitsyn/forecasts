package by.forecasts.dao;

import by.forecasts.dao.common.BaseDaoImpl;
import by.forecasts.entities.Forecast;
import by.forecasts.utils.SessionManager;
import org.hibernate.Session;

import java.util.List;


public final class ForecastDaoImpl extends BaseDaoImpl<Forecast> {

    private static ForecastDaoImpl instance;

    private ForecastDaoImpl() {
        super(Forecast.class);
    }

    public static ForecastDaoImpl getInstance() {
        if (instance == null) {
            synchronized (ForecastDaoImpl.class) {
                if (instance == null) {
                    instance = new ForecastDaoImpl();
                }
            }
        }
        return instance;
    }

    public List<Forecast> getAllForecastsOfUser(Long userId) {
        Session session = SessionManager.getSession();
        session.beginTransaction();

        List<Forecast> forecasts = session.createQuery("select f from Forecast f where f.user.id = :userId", Forecast.class)
                .setParameter("userId", userId)
                .getResultList();

        session.getTransaction().commit();
        session.close();
        return forecasts;
    }

    public List<Object[]> getUserForecastsOfTournament(Long userId, Long tournamentId, Long matchState, int recordsCnt, int pageNo) {
        Session session = SessionManager.getSession();
        session.beginTransaction();

        List<Object[]> resultList = session.createQuery("select f.match.firstTeam.teamName, f.match.secondTeam.teamName, "
                + "f.matchForecast.firstResult, f.matchForecast.secondResult from Forecast f where f.user.id = :userId "
                + "and f.match.tournament.id = :tournamentId and f.match.matchState = :matchState "
                + "order by f.match.id", Object[].class)
                .setParameter("userId", userId)
                .setParameter("tournamentId", tournamentId)
                .setParameter("matchState", matchState)
                .setFirstResult((recordsCnt * pageNo) - recordsCnt)
                .setMaxResults(recordsCnt)
                .getResultList();

        session.getTransaction().commit();
        session.close();
        return resultList;
    }

    public Long getCountOfUserForecasts(Long userId, Long tournamentId, Long matchState) {
        Session session = SessionManager.getSession();
        session.beginTransaction();

        Long forecastsCount = session.createQuery("select count(*) from Forecast f where f.user.id = :userId "
                + "and f.match.tournament.id = :tournamentId and f.match.matchState = :matchState "
                + "order by f.match.id", Long.class)
                .setParameter("userId", userId)
                .setParameter("tournamentId", tournamentId)
                .setParameter("matchState", matchState)
                .getSingleResult();

        session.getTransaction().commit();
        session.close();
        return forecastsCount;
    }
}
