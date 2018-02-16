package by.forecasts.service;

import by.forecasts.dao.ForecastDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ForecastServiceImpl implements ForecastService {

    private final ForecastDao forecastDao;

    @Autowired
    public ForecastServiceImpl(ForecastDao forecastDao) {
        this.forecastDao = forecastDao;
    }

    @Override
    public List<Object[]> getUserForecasts(Long tournamentId, Long userId, Long matchStateId, int recordsCnt, int pageNo) {
        return forecastDao.getUserForecastsOfTournament(userId, tournamentId, matchStateId, recordsCnt, pageNo);
    }

    @Override
    public Long getCountOfUserForecasts(Long tournamentId, Long userId, Long matchStateId) {
        return forecastDao.getCountOfUserForecasts(userId, tournamentId, matchStateId);
    }
}
