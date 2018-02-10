package by.forecasts.dao;

import by.forecasts.entities.RegularGroup;
import by.forecasts.entities.Team;
import by.forecasts.entities.TournamentGroup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class TournamentGroupDao extends BaseDao<TournamentGroup> {

    private static TournamentGroupDao INSTANCE;
    private static final SessionFactory SESSION_FACTORY = new Configuration().configure("hibernate_h2.cfg.xml").buildSessionFactory();


    private TournamentGroupDao() {
        super(TournamentGroup.class);
    }

    public static TournamentGroupDao getInstance() {
        if (INSTANCE == null) {
            synchronized (TournamentGroupDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TournamentGroupDao();
                }
            }
        }
        return INSTANCE;
    }

    public List<RegularGroup> getGroupsOfTournament(Long tournamentId) {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();

        List<RegularGroup> groups = session.createQuery("select g from RegularGroup g where g.tournament.id = " +
                ":tournamentId", RegularGroup.class)
                .setParameter("tournamentId", tournamentId)
                .getResultList();

        session.getTransaction().commit();
        session.close();
        return groups;
    }
}
