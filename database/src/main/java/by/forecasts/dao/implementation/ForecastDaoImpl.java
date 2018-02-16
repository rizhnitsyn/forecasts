package by.forecasts.dao.implementation;

import by.forecasts.dao.ForecastDao;
import by.forecasts.dao.common.BaseDaoImpl;
import by.forecasts.entities.Forecast;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ForecastDaoImpl extends BaseDaoImpl<Forecast> implements ForecastDao {

    @Override
    public List<Forecast> getAllForecastsOfUser(Long userId) {
       return getSessionFactory().getCurrentSession()
               .createQuery("select f from Forecast f where f.user.id = :userId", Forecast.class)
               .setParameter("userId", userId)
               .getResultList();
    }

    @Override
    public List<Object[]> getUserForecastsOfTournament(Long userId, Long tournamentId, Long matchState, int recordsCnt, int pageNo) {
        return getSessionFactory().getCurrentSession()
                .createQuery("select f.match.firstTeam.teamName, f.match.secondTeam.teamName, "
                + "f.matchForecast.firstResult, f.matchForecast.secondResult from Forecast f where f.user.id = :userId "
                + "and f.match.tournament.id = :tournamentId and f.match.matchState = :matchState "
                + "order by f.match.id", Object[].class)
                .setParameter("userId", userId)
                .setParameter("tournamentId", tournamentId)
                .setParameter("matchState", matchState)
                .setFirstResult((recordsCnt * pageNo) - recordsCnt)
                .setMaxResults(recordsCnt)
                .getResultList();
    }

    @Override
    public Long getCountOfUserForecasts(Long userId, Long tournamentId, Long matchState) {
        List<Long> results = getSessionFactory().getCurrentSession()
                .createQuery("select count(*) from Forecast f where f.user.id = :userId "
                        + "and f.match.tournament.id = :tournamentId and f.match.matchState = :matchState "
                        + "order by f.match.id", Long.class)
                .setParameter("userId", userId)
                .setParameter("tournamentId", tournamentId)
                .setParameter("matchState", matchState)
                .getResultList();

        return !results.isEmpty() ? results.get(0) : null;
    }
}
