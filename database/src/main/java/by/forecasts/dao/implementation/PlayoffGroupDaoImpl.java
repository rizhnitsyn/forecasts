package by.forecasts.dao.implementation;

import by.forecasts.dao.PlayoffGroupDao;
import by.forecasts.entities.PlayoffGroup;
import org.springframework.stereotype.Repository;

import java.util.List;


public class PlayoffGroupDaoImpl  implements PlayoffGroupDao {

    @Override
    public List<PlayoffGroup> getGroupsOfTournament(Long tournamentId) {
        return null;
//        return getSessionFactory().getCurrentSession()
//                .createQuery("select g from PlayoffGroup g where g.tournament.id = "
//                        + ":tournamentId", PlayoffGroup.class)
//                .setParameter("tournamentId", tournamentId)
//                .getResultList();
    }
}
