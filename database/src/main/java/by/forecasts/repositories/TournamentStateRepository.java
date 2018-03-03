package by.forecasts.repositories;

import by.forecasts.entities.TournamentState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentStateRepository extends JpaRepository<TournamentState, Long> {
}
