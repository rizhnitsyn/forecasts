package by.forecasts.dao;

import by.forecasts.entities.Forecast;


public class ForecastDao extends BaseDao<Forecast> {

    private static ForecastDao INSTANCE;
//    private SessionFactory SESSION_FACTORY = new Configuration().configure("hibernate_h2.cfg.xml").buildSessionFactory();

    private ForecastDao() {
        super(Forecast.class);
    }

    public static ForecastDao getInstance() {
        if (INSTANCE == null) {
            synchronized (ForecastDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ForecastDao();
                }
            }
        }
        return INSTANCE;
    }


}
