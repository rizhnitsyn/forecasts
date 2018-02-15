package by.forecasts.dao;

import by.forecasts.dao.common.BaseDaoImpl;
import by.forecasts.entities.Team;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public final class TeamDaoImpl extends BaseDaoImpl<Team> implements TeamDao {

    @Override
    public Team getTeamOrganizer(Long tournamentId) {
        List<Team> results = getSessionFactory().getCurrentSession()
                .createQuery("select t.organizer from Tournament t where t.id = :tournamentId", Team.class)
                .setParameter("tournamentId", tournamentId)
                .getResultList();

        return !results.isEmpty() ? results.get(0) : null;
    }
}
