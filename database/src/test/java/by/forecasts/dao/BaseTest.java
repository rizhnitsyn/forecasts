package by.forecasts.dao;

import by.forecasts.utils.SessionManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;

public abstract class BaseTest {

    protected static final TeamDao TEAM_DAO = TeamDao.getInstance();
    protected static final TournamentDao TOURNAMENT_DAO = TournamentDao.getInstance();
    protected static final UserDao USER_DAO = UserDao.getInstance();
    protected static final MatchDao MATCH_DAO = MatchDao.getInstance();
    protected static final ForecastDao FORECAST_DAO = ForecastDao.getInstance();
    protected static final TournamentGroupDao TOURNAMENT_GROUP_DAO = TournamentGroupDao.getInstance();


    @Before
    public void initSessionFactory() {
        SessionManager.setH2Config();
    }

    @After
    public void finish() {
        SessionManager.getSessionFactory().close();
    }

    @AfterClass
    public static void initDef() {
        SessionManager.setMySqlConfig();
    }
}
