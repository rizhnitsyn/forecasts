package by.forecasts;

import by.forecasts.dao.ForecastDaoImpl;

import java.util.List;

public final class ForecastService {

    private static ForecastService instance;
    private static final ForecastDaoImpl FORECAST_DAO = ForecastDaoImpl.getInstance();

    private ForecastService() {}

    public static ForecastService getInstance() {
        if (instance == null) {
            synchronized (ForecastService.class) {
                if (instance == null) {
                    instance = new ForecastService();
                }
            }
        }
        return instance;
    }

    public List<Object[]> getUserForecasts(Long tournamentId, Long userId, Long matchStateId, int recordsCnt, int pageNo) {
        return FORECAST_DAO.getUserForecastsOfTournament(userId, tournamentId, matchStateId, recordsCnt, pageNo);
    }

    public Long getCountOfUserForecasts(Long tournamentId, Long userId, Long matchStateId) {
        return FORECAST_DAO.getCountOfUserForecasts(userId, tournamentId, matchStateId);
    }
}
