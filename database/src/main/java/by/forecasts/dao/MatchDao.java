package by.forecasts.dao;

import by.forecasts.entities.Match;

public class MatchDao extends BaseDao<Match> {

    private static MatchDao INSTANCE;

    private MatchDao() {
        super(Match.class);
    }

    public static MatchDao getInstance() {
        if (INSTANCE == null) {
            synchronized (MatchDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MatchDao();
                }
            }
        }
        return INSTANCE;
    }
}
