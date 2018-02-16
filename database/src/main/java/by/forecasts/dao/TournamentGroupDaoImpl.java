package by.forecasts.dao;

import by.forecasts.dao.common.BaseDaoImpl;
import by.forecasts.entities.RegularGroup;
import by.forecasts.entities.TournamentGroup;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public final class TournamentGroupDaoImpl extends BaseDaoImpl<TournamentGroup> implements TournamentGroupDao {

    public List<RegularGroup> getGroupsOfTournament(Long tournamentId) {
        return getSessionFactory().getCurrentSession()
                .createQuery("select g from RegularGroup g where g.tournament.id = "
                + ":tournamentId", RegularGroup.class)
                .setParameter("tournamentId", tournamentId)
                .getResultList();
    }
}
