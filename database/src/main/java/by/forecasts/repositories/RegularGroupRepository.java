package by.forecasts.repositories;

import by.forecasts.entities.RegularGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegularGroupRepository extends JpaRepository<RegularGroup, Long> {

//    RegularGroup getByTournament(Long id);

    List<RegularGroup> findAllByTournamentId(Long id);

}
