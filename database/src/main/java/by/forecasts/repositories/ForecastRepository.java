package by.forecasts.repositories;

import by.forecasts.entities.Forecast;
import org.springframework.data.repository.CrudRepository;

public interface ForecastRepository extends CrudRepository<Forecast, Long> {
}
