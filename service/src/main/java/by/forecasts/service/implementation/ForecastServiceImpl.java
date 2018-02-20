package by.forecasts.service.implementation;

import by.forecasts.repositories.ForecastRepository;
import by.forecasts.service.ForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ForecastServiceImpl implements ForecastService {

    private final ForecastRepository forecastRepository;

    @Autowired
    public ForecastServiceImpl(ForecastRepository forecastRepository) {
        this.forecastRepository = forecastRepository;
    }

    @Override
    public List<Object[]> getUserForecasts(Long tournamentId, Long userId, Long matchStateId, int recordsCnt, int pageNo) {
//        return forecastRepository.getUserForecastsOfTournament(userId, tournamentId, matchStateId, recordsCnt, pageNo);
        return null;
    }

    @Override
    public Long getCountOfUserForecasts(Long tournamentId, Long userId, Long matchStateId) {
        return null;
//        return forecastRepository.getCountOfUserForecasts(userId, tournamentId, matchStateId);
    }
}
