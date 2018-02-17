package by.forecasts.dao.implementation;

import by.forecasts.dao.RegularGroupDao;
import by.forecasts.dao.common.BaseDaoImpl;
import by.forecasts.entities.RegularGroup;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RegularGroupDaoImpl extends BaseDaoImpl<RegularGroup> implements RegularGroupDao {

    @Override
    public List<RegularGroup> getGroupsOfTournament(Long tournamentId) {
        return getSessionFactory().getCurrentSession()
                .createQuery("select g from RegularGroup g where g.tournament.id = "
                        + ":tournamentId", RegularGroup.class)
                .setParameter("tournamentId", tournamentId)
                .getResultList();
    }
}
