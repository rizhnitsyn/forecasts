package by.forecasts.dao.implementation;

import by.forecasts.dao.GroupDao;
import by.forecasts.dao.common.BaseDaoImpl;
import by.forecasts.entities.Group;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public final class GroupDaoImpl extends BaseDaoImpl<Group> implements GroupDao {

    @Override
    public List<Group> getGroupsOfTournament(Long tournamentId) {
        return getSessionFactory().getCurrentSession()
                .createQuery("select g from Group g where g.tournament.id = "
                + ":tournamentId", Group.class)
                .setParameter("tournamentId", tournamentId)
                .getResultList();
    }
}
