package by.forecasts.dao.implementation;

import by.forecasts.dao.MatchDao;
import by.forecasts.entities.Match;
import org.springframework.stereotype.Repository;

import java.util.List;


public class MatchDaoImpl   implements MatchDao {

    @Override
    public List<Match> getMatchesForForecast(Long tournamentId, Long userId) {
        return null;
//        return getSessionFactory().getCurrentSession()
//                .createQuery("select m from Match m where m.tournament.id = :trId "
//                + "and m.id not in (select f.match.id from Forecast f where f.user.id = :userId)", Match.class)
//                .setParameter("trId", tournamentId)
//                .setParameter("userId", userId)
//                .getResultList();
    }
}
