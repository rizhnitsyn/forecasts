package by.forecasts.dao;

import by.forecasts.entities.TournamentGroup;

public class TournamentGroupDao extends BaseDao<TournamentGroup> {

    private static TournamentGroupDao INSTANCE;

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
}
