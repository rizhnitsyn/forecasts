package by.forecasts.repositories;

import by.forecasts.config.TestDatabaseConfig;
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

    @Autowired
    protected MatchStateRepository matchStateRepository;

    @Autowired
    protected UserStateRepository userStateRepository;

    @Autowired
    TournamentStateRepository tournamentStateRepository;
}
