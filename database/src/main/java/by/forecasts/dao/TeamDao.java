package by.forecasts.dao;

import by.forecasts.entities.Team;
import by.forecasts.utils.SessionManager;
import org.hibernate.Session;

public final class TeamDao extends BaseDao<Team> {

    private static TeamDao instance;

    private TeamDao() {
        super(Team.class);
    }

    public static TeamDao getInstance() {
        if (instance == null) {
            synchronized (TeamDao.class) {
                if (instance == null) {
                    instance = new TeamDao();
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
