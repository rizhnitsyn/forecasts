package by.forecasts.dao.implementation;

import by.forecasts.dao.TeamDao;
import by.forecasts.entities.Team;
import org.springframework.stereotype.Repository;


public final class TeamDaoImpl implements TeamDao {

    @Override
    public Team getTeamOrganizer(Long tournamentId) {
        return null;
//        List<Team> results = getSessionFactory().getCurrentSession()
//                .createQuery("select t.organizer from Tournament t where t.id = :tournamentId", Team.class)
//                .setParameter("tournamentId", tournamentId)
//                .getResultList();
//
//        return !results.isEmpty() ? results.get(0) : null;
    }
}
