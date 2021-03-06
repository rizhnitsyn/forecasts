package by.forecasts.service.implementation;

import by.forecasts.utils.ForecastFilter;
import by.forecasts.entities.Forecast;
import by.forecasts.entities.Match;
import by.forecasts.entities.MatchScore;
import by.forecasts.entities.User;
import by.forecasts.repositories.ForecastRepository;
import by.forecasts.repositories.MatchRepository;
import by.forecasts.repositories.UserRepository;
import by.forecasts.service.ForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ForecastServiceImpl implements ForecastService {

    private final ForecastRepository forecastRepository;
    private final MatchRepository matchRepository;
    private final UserRepository userRepository;

    @Autowired
    public ForecastServiceImpl(ForecastRepository forecastRepository, MatchRepository matchRepository, UserRepository userRepository) {
        this.forecastRepository = forecastRepository;
        this.matchRepository = matchRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Page<Forecast> getUserForecasts(ForecastFilter forecastFilter) {
        PageRequest pageRequest = new PageRequest(forecastFilter.getPageNo(), forecastFilter.getRecordsOnPage());
        return forecastRepository.findAllByUserIdAndMatchTournamentIdAndMatchMatchStateId(
                forecastFilter.getUserId(),
                forecastFilter.getTournamentId(),
                forecastFilter.getMatchStateId(),
                pageRequest);
    }

    @Override
    public Long getCountOfUserForecasts(Long userId, Long tournamentId, Long matchStateId) {
        return forecastRepository.countAllByUserIdAndMatchTournamentIdAndMatchMatchStateId(userId, tournamentId, matchStateId);
    }

    @Override
    public void saveForecast(MatchScore score, Long matchId, Long userId) {
        Forecast forecast = forecastRepository.findOneByUserIdAndMatchId(userId, matchId);
        if (forecast == null) {
            Match match = matchRepository.findOne(matchId);
            User user = userRepository.findOne(userId);
            forecast = new Forecast(score, user, match);
            forecastRepository.save(forecast);
        } else {
            forecast.setMatchForecast(score);
        }
    }

    @Override
    public List<Forecast> findAllByUserIdAndTournamentId(Long userId, Long tournamentsId) {
        return forecastRepository.findAllByUserIdAndMatchTournamentId(userId, tournamentsId);
    }
}
