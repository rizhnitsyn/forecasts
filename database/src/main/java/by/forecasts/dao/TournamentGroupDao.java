package by.forecasts.dao;

import by.forecasts.entities.RegularGroup;
import by.forecasts.entities.TournamentGroup;
import by.forecasts.utils.SessionManager;
import org.hibernate.Session;

import java.util.List;

public final class TournamentGroupDao extends BaseDao<TournamentGroup> {

    private static TournamentGroupDao instance;

    private TournamentGroupDao() {
        super(TournamentGroup.class);
    }

    public static TournamentGroupDao getInstance() {
        if (instance == null) {
            synchronized (TournamentGroupDao.class) {
                if (instance == null) {
                    instance = new TournamentGroupDao();
                }
            }
        }
        return instance;
    }

    public List<RegularGroup> getGroupsOfTournament(Long tournamentId) {
        Session session = SessionManager.getSession();
        session.beginTransaction();

        List<RegularGroup> groups = session.createQuery("select g from RegularGroup g where g.tournament.id = "
                + ":tournamentId", RegularGroup.class)
                .setParameter("tournamentId", tournamentId)
                .getResultList();

        session.getTransaction().commit();
        session.close();
        return groups;
    }
}
