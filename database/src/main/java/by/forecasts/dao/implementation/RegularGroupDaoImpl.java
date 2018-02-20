package by.forecasts.dao.implementation;

import by.forecasts.dao.RegularGroupDao;
import by.forecasts.entities.RegularGroup;
import org.springframework.stereotype.Repository;

import java.util.List;


public class RegularGroupDaoImpl implements RegularGroupDao {

    @Override
    public List<RegularGroup> getGroupsOfTournament(Long tournamentId) {
        return null;
//        return getSessionFactory().getCurrentSession()
//                .createQuery("select g from RegularGroup g where g.tournament.id = "
//                        + ":tournamentId", RegularGroup.class)
//                .setParameter("tournamentId", tournamentId)
//                .getResultList();
    }
}
