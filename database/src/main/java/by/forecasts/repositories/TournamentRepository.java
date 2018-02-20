package by.forecasts.repositories;

import by.forecasts.entities.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {

//    List<Tournament> getAllByUsers_id(Long id);
}
