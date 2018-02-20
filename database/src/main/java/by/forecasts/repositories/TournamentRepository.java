package by.forecasts.repositories;

import by.forecasts.entities.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {

    List<Tournament> getAllByUsers_id(Long userId);
}
