package by.forecasts.dao;

import by.forecasts.config.TestDatabaseConfig;
import by.forecasts.dao.implementation.ForecastDaoImpl;
import by.forecasts.dao.implementation.GroupDaoImpl;
import by.forecasts.dao.implementation.MatchDaoImpl;
import by.forecasts.dao.implementation.TeamDaoImpl;
import by.forecasts.dao.implementation.TournamentDaoImpl;
import by.forecasts.dao.implementation.UserDaoImpl;
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

    @Autowired
    protected ForecastDaoImpl forecastDao;

    @Autowired
    protected MatchDaoImpl matchDao;

    @Autowired
    protected TournamentDaoImpl tournamentDao;

    @Autowired
    protected TeamDaoImpl teamDao;

    @Autowired
    protected GroupDaoImpl groupDao;

    @Autowired
    protected PlayoffGroupDao playoffGroupDao;

    @Autowired
    protected RegularGroupDao regularGroupDao;

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
