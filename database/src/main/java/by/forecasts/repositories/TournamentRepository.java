package by.forecasts.repositories;

import by.forecasts.entities.Tournament;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TournamentRepository extends CrudRepository<Tournament, Long> {

    List<Tournament> getAllByUsers_id(Long id);
}
