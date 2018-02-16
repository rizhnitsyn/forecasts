package by.forecasts.dao;

import by.forecasts.dao.common.BaseDaoImpl;
import by.forecasts.entities.RegularGroup;
import by.forecasts.entities.TournamentGroup;
import by.forecasts.utils.SessionManager;
import org.hibernate.Session;

import java.util.List;

public final class TournamentGroupDaoImpl extends BaseDaoImpl<TournamentGroup> {

    private static TournamentGroupDaoImpl instance;

    private TournamentGroupDaoImpl() {
        super(TournamentGroup.class);
    }

    public static TournamentGroupDaoImpl getInstance() {
        if (instance == null) {
            synchronized (TournamentGroupDaoImpl.class) {
                if (instance == null) {
                    instance = new TournamentGroupDaoImpl();
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
