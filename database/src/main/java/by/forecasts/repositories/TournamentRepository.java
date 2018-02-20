package by.forecasts.repositories;

import by.forecasts.entities.Tournament;
import org.springframework.data.repository.CrudRepository;

public interface TournamentRepository extends CrudRepository<Tournament, Long> {

//    List<Tournament> getAllByUsers_id(Long id);
}
