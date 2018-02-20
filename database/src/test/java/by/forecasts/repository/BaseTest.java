package by.forecasts.repository;

import by.forecasts.config.TestDatabaseConfig;
import by.forecasts.repositories.ForecastRepository;
import by.forecasts.repositories.GroupRepository;
import by.forecasts.repositories.MatchRepository;
import by.forecasts.repositories.PlayoffGroupRepository;
import by.forecasts.repositories.RegularGroupRepository;
import by.forecasts.repositories.TeamRepository;
import by.forecasts.repositories.TournamentRepository;
import by.forecasts.repositories.UserRepository;
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
    protected ForecastRepository forecastRepository;

    @Autowired
    protected MatchRepository matchRepository;

    @Autowired
    protected TournamentRepository tournamentRepository;

    @Autowired
    protected TeamRepository teamRepository;

    @Autowired
    protected GroupRepository groupRepository;

    @Autowired
    protected PlayoffGroupRepository playoffGroupRepository;

    @Autowired
    protected RegularGroupRepository regularGroupRepository;

    @Autowired
    protected UserRepository userRepository;
}
