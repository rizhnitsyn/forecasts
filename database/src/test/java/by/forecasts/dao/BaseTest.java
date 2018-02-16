package by.forecasts.dao;

import by.forecasts.config.TestDatabaseConfig;
import by.forecasts.utils.SessionManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@Transactional
@ContextConfiguration(classes = TestDatabaseConfig.class)
public abstract class BaseTest {

//    protected static final TeamDaoImpl teamDao = TeamDaoImpl.getInstance();
//    protected static final TournamentDaoImpl tournamentDao = TournamentDaoImpl.getInstance();
//    protected static final UserDaoImpl userDao = UserDaoImpl.getInstance();
//    protected static final MatchDaoImpl matchDao = MatchDaoImpl.getInstance();
//    protected static final ForecastDaoImpl forecastDao = ForecastDaoImpl.getInstance();
//    protected static final TournamentGroupDaoImpl tournamentGroupDao = TournamentGroupDaoImpl.getInstance();

    @Autowired
    protected ForecastDaoImpl forecastDao;

    @Autowired
    protected MatchDaoImpl matchDao;

    @Autowired
    protected TournamentDaoImpl tournamentDao;

    @Autowired
    protected TeamDaoImpl teamDao;

    @Autowired
    protected TournamentGroupDaoImpl tournamentGroupDao;

    @Autowired
    protected UserDaoImpl userDao;

    @Before
    public void initSessionFactory() {
//        SessionManager.setH2Config();
    }

    @After
    public void finish() {
//        SessionManager.getSessionFactory().close();
    }

    @AfterClass
    public static void initDef() {
//        SessionManager.setMySqlConfig();
    }
}
