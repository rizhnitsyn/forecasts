package by.forecasts.dao.implementation;

import by.forecasts.dao.GroupDao;
import by.forecasts.entities.Group;
import org.springframework.stereotype.Repository;

import java.util.List;


public final class GroupDaoImpl implements GroupDao {

    @Override
    public List<Group> getGroupsOfTournament(Long tournamentId) {
        return null;
//        return getSessionFactory().getCurrentSession()
//                .createQuery("select g from Group g where g.tournament.id = "
//                + ":tournamentId", Group.class)
//                .setParameter("tournamentId", tournamentId)
//                .getResultList();
    }
}
