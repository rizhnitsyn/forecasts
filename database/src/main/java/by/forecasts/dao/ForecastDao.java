package by.forecasts.dao;

import by.forecasts.entities.Forecast;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class ForecastDao extends BaseDao<Forecast> {

    private static ForecastDao INSTANCE;
    private SessionFactory SESSION_FACTORY = new Configuration().configure("hibernate_h2.cfg.xml").buildSessionFactory();

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

    public List<Forecast> getUserForecastsOfTournament(Long userId, Long tournamentId, Long matchState) {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();

        List<Forecast> forecasts = session.createQuery("select f from Forecast f where f.user.id = :userId " +
                "and f.match.tournament.id = :tournamentId and f.match.matchState = :matchState", Forecast.class)
                .setParameter("userId", userId)
                .setParameter("tournamentId", tournamentId)
                .setParameter("matchState", matchState)
                .getResultList();

        session.getTransaction().commit();
        session.close();
        return forecasts;
    }


}
