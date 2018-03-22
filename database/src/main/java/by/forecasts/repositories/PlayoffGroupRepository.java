package by.forecasts.repositories;

import by.forecasts.entities.PlayoffGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayoffGroupRepository extends JpaRepository<PlayoffGroup, Long> {

    List<PlayoffGroup> findAllByTournamentId(Long tournamentId);
}
