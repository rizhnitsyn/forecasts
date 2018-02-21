package by.forecasts.repositories;

import by.forecasts.entities.Forecast;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ForecastRepository extends JpaRepository<Forecast, Long> {

    Page<Forecast> findAllByUserIdAndMatchTournamentIdAndMatchMatchState(Long userId, Long tournamentId, Long matchStateId, Pageable pageable);

    Long countAllByUserIdAndMatchTournamentIdAndMatchMatchState(Long userId, Long tournamentId, Long matchStateId);
}
