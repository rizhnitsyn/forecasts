package by.forecasts.service.implementation;

import by.forecasts.entities.Forecast;
import by.forecasts.repositories.ForecastRepository;
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

    @Autowired
    public ForecastServiceImpl(ForecastRepository forecastRepository) {
        this.forecastRepository = forecastRepository;
    }

    @Override
    public List<Forecast> getUserForecasts(Long tournamentId, Long userId, Long matchStateId, int recordsCnt, int pageNo) {
        PageRequest pageRequest = new PageRequest(pageNo, recordsCnt);
        Page<Forecast> forecastPage = forecastRepository.findAllByUserIdAndMatchTournamentIdAndMatchMatchState(userId, tournamentId, matchStateId, pageRequest);
        return forecastPage.getContent();
    }

    @Override
    public Long getCountOfUserForecasts(Long userId, Long tournamentId, Long matchStateId) {
        return forecastRepository.countAllByUserIdAndMatchTournamentIdAndMatchMatchState(userId, tournamentId, matchStateId);
    }
}
