package by.forecasts.dao;

import by.forecasts.utils.SessionManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;

public abstract class BaseTest {

    protected static final TeamDaoImpl TEAM_DAO = TeamDaoImpl.getInstance();
    protected static final TournamentDaoImpl TOURNAMENT_DAO = TournamentDaoImpl.getInstance();
    protected static final UserDaoImpl USER_DAO = UserDaoImpl.getInstance();
    protected static final MatchDaoImpl MATCH_DAO = MatchDaoImpl.getInstance();
    protected static final ForecastDaoImpl FORECAST_DAO = ForecastDaoImpl.getInstance();
    protected static final TournamentGroupDaoImpl TOURNAMENT_GROUP_DAO = TournamentGroupDaoImpl.getInstance();


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
