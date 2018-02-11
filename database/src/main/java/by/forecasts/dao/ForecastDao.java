package by.forecasts.dao;

import by.forecasts.entities.Forecast;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class ForecastDao extends BaseDao<Forecast> {

    private static ForecastDao INSTANCE;
    private SessionFactory SESSION_FACTORY = new Configuration().configure("hibernate_h2.cfg.xml").buildSessionFactory();
//    private SessionFactory SESSION_FACTORY = new Configuration().configure("hibernate_mysql.cfg.xml").buildSessionFactory();

    private ForecastDao() {
        super(Forecast.class);
    }

    public static ForecastDao getInstance() {
        if (INSTANCE == null) {
            synchronized (ForecastDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ForecastDao();
                }
            }
        }
        return INSTANCE;
    }

    public List<Forecast> getAllForecastsOfUser(Long userId) {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();

        List<Forecast> forecasts = session.createQuery("select f from Forecast f where f.user.id = :userId", Forecast.class)
                .setParameter("userId", userId)
                .getResultList();

        session.getTransaction().commit();
        session.close();
        return forecasts;
    }

    public List<Object[]> getUserForecastsOfTournament(Long userId, Long tournamentId, Long matchState, int recordsCnt, int pageNo) {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();

        List<Object[]> resultList = session.createQuery("select f.match.firstTeam.teamName, f.match.secondTeam.teamName, " +
                " f.matchForecast.firstResult, f.matchForecast.secondResult from Forecast f where f.user.id = :userId " +
                "and f.match.tournament.id = :tournamentId and f.match.matchState = :matchState " +
                "order by f.match.id", Object[].class)
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
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();

        Long forecastsCount = session.createQuery("select count(*) from Forecast f where f.user.id = :userId " +
                "and f.match.tournament.id = :tournamentId and f.match.matchState = :matchState " +
                "order by f.match.id", Long.class)
                .setParameter("userId", userId)
                .setParameter("tournamentId", tournamentId)
                .setParameter("matchState", matchState)
                .getSingleResult();

        session.getTransaction().commit();
        session.close();
        return forecastsCount;
    }


}
