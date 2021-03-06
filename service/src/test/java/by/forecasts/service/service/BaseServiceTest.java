package by.forecasts.service.service;


import by.forecasts.service.ForecastService;
import by.forecasts.service.GroupService;
import by.forecasts.service.MatchService;
import by.forecasts.service.PlayoffGroupService;
import by.forecasts.service.RegularGroupService;
import by.forecasts.service.TeamService;
import by.forecasts.service.TournamentService;
import by.forecasts.service.UserService;
import by.forecasts.service.config.TestServiceConfig;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@Transactional
@ContextConfiguration(classes = TestServiceConfig.class)
public abstract class BaseServiceTest {

    @Autowired
    protected ForecastService forecastService;

    @Autowired
    protected MatchService matchService;

    @Autowired
    protected TournamentService tournamentService ;

    @Autowired
    protected TeamService teamService;

    @Autowired
    protected GroupService groupService;

    @Autowired
    protected PlayoffGroupService playoffGroupService;

    @Autowired
    protected RegularGroupService regularGroupService;

    @Autowired
    protected UserService userService;
}
