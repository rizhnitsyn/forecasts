package by.forecasts.repositories;

import by.forecasts.entities.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {
}
