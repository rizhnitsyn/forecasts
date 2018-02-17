package by.forecasts.dao.implementation;

import by.forecasts.dao.PlayoffGroupDao;
import by.forecasts.dao.common.BaseDaoImpl;
import by.forecasts.entities.PlayoffGroup;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlayoffGroupDaoImpl extends BaseDaoImpl<PlayoffGroup> implements PlayoffGroupDao {

    @Override
    public List<PlayoffGroup> getGroupsOfTournament(Long tournamentId) {
        return getSessionFactory().getCurrentSession()
                .createQuery("select g from PlayoffGroup g where g.tournament.id = "
                        + ":tournamentId", PlayoffGroup.class)
                .setParameter("tournamentId", tournamentId)
                .getResultList();
    }
}
