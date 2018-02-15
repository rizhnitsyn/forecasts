package by.forecasts.services;

import by.forecasts.dao.ForecastDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ForecastService {

    private final ForecastDao forecastDao;

    @Autowired
    public ForecastService(ForecastDao forecastDao) {
        this.forecastDao = forecastDao;
    }


    public List<Object[]> getUserForecasts(Long tournamentId, Long userId, Long matchStateId, int recordsCnt, int pageNo) {
        return forecastDao.getUserForecastsOfTournament(userId, tournamentId, matchStateId, recordsCnt, pageNo);
    }

    public Long getCountOfUserForecasts(Long tournamentId, Long userId, Long matchStateId) {
        return forecastDao.getCountOfUserForecasts(userId, tournamentId, matchStateId);
    }
}
