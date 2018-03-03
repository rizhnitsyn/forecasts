package by.forecasts.service.implementation;

import by.forecasts.dto.ForecastFilter;
import by.forecasts.entities.Forecast;
import by.forecasts.repositories.ForecastRepository;
import by.forecasts.service.ForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ForecastServiceImpl implements ForecastService {

    private final ForecastRepository forecastRepository;

    @Autowired
    public ForecastServiceImpl(ForecastRepository forecastRepository) {
        this.forecastRepository = forecastRepository;
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
}
