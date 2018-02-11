package by.forecasts.dao;

import by.forecasts.entities.Team;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TeamDao extends BaseDao<Team>{

    private static final SessionFactory SESSION_FACTORY = new Configuration().configure("hibernate_h2.cfg.xml").buildSessionFactory();
//    private static final SessionFactory SESSION_FACTORY = new Configuration().configure("hibernate_mysql.cfg.xml").buildSessionFactory();
    private static TeamDao INSTANCE;

    private TeamDao() {
        super(Team.class);
    }

    public static TeamDao getInstance() {
        if (INSTANCE == null) {
            synchronized (TeamDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TeamDao();
                }
            }
        }
        return INSTANCE;
    }

    public Team getTeamOrganizer(Long tournamentId) {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();

        Team team = session.createQuery("select t.organizer from Tournament t where t.id = :tournamentId", Team.class)
                .setParameter("tournamentId", tournamentId)
                .getSingleResult();

        session.getTransaction().commit();
        session.close();
        return team;
    }

}
