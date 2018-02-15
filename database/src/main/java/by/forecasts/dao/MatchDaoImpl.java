package by.forecasts.dao;

import by.forecasts.dao.common.BaseDaoImpl;
import by.forecasts.entities.Match;
import by.forecasts.utils.SessionManager;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public final class MatchDaoImpl extends BaseDaoImpl<Match>  implements MatchDao {

    @Override
    public List<Match> getMatchesForForecast(Long tournamentId, Long userId) {
        return getSessionFactory().getCurrentSession()
                .createQuery("select m from Match m where m.tournament.id = :trId "
                + "and m.id not in (select f from Forecast f where f.user.id = :userId)", Match.class)
                .setParameter("trId", tournamentId)
                .setParameter("userId", userId)
                .getResultList();
    }
}
