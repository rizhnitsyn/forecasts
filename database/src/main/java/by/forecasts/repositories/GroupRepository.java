package by.forecasts.repositories;

import by.forecasts.entities.Group;
import by.forecasts.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {

    List<Group> getAllByTournamentId(Long id);
}
