package by.forecasts.repositories;

import by.forecasts.entities.Forecast;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ForecastRepository extends JpaRepository<Forecast, Long> {

    Page<Forecast> findAllByUserIdAndMatchTournamentIdAndMatchMatchStateId(Long userId, Long tournamentId, Long matchStateId, Pageable pageable);

    Long countAllByUserIdAndMatchTournamentIdAndMatchMatchStateId(Long userId, Long tournamentId, Long matchStateId);

    Forecast findOneByUserIdAndMatchId(Long userId, Long matchId);

    List<Forecast> findAllByUserIdAndMatchTournamentId(Long userId, Long tournamentId);
}
