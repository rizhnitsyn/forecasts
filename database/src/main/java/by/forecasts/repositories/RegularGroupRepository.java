package by.forecasts.repositories;

import by.forecasts.entities.RegularGroup;
import by.forecasts.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegularGroupRepository extends JpaRepository<RegularGroup, Long> {

    RegularGroup getByTournament(Long id);

    List<RegularGroup> findAllByTournamentId(Long id);
}
