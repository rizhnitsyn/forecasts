package by.forecasts.dao;

import by.forecasts.dao.common.BaseDaoImpl;
import by.forecasts.entities.Team;
import by.forecasts.utils.SessionManager;
import org.hibernate.Session;

public final class TeamDaoImpl extends BaseDaoImpl<Team> {

    private static TeamDaoImpl instance;

    private TeamDaoImpl() {
        super(Team.class);
    }

    public static TeamDaoImpl getInstance() {
        if (instance == null) {
            synchronized (TeamDaoImpl.class) {
                if (instance == null) {
                    instance = new TeamDaoImpl();
                }
            }
        }
        return instance;
    }

    public Team getTeamOrganizer(Long tournamentId) {
        Session session = SessionManager.getSession();
        session.beginTransaction();

        Team team = session.createQuery("select t.organizer from Tournament t where t.id = :tournamentId", Team.class)
                .setParameter("tournamentId", tournamentId)
                .getSingleResult();

        session.getTransaction().commit();
        session.close();
        return team;
    }
}
