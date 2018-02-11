package by.forecasts;

import by.forecasts.dao.ForecastDao;
import by.forecasts.entities.Forecast;

import java.util.List;

public class ForecastService {

    private static ForecastService INSTANCE;
    private static final ForecastDao FORECAST_DAO = ForecastDao.getInstance();

    private ForecastService() {}

    public static ForecastService getInstance() {
        if (INSTANCE == null) {
            synchronized (ForecastService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ForecastService();
                }
            }
        }
        return INSTANCE;
    }

    public List<Object[]> getUserForecasts(Long tournamentId, Long userId, Long matchStateId, int recordsCnt, int pageNo) {
        return FORECAST_DAO.getUserForecastsOfTournament(userId, tournamentId, matchStateId, recordsCnt, pageNo);
    }

    public Long getCountOfUserForecasts(Long tournamentId, Long userId, Long matchStateId) {
        return FORECAST_DAO.getCountOfUserForecasts(userId, tournamentId, matchStateId);
    }
}
