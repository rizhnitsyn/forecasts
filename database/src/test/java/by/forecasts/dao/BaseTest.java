package by.forecasts.dao;

import by.forecasts.config.TestDatabaseConfig;
import org.hibernate.SessionFactory;
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
