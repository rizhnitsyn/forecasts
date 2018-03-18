package by.forecasts.repositories;

import by.forecasts.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {

    List<Group> getAllByTournamentIdOrderById(Long id);

    Group findOneByTournamentIdAndTeamsInGroupIdIn(Long tournamentId, List<Long> list);
}
