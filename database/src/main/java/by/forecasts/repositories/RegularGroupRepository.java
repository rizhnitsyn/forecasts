package by.forecasts.repositories;

import by.forecasts.entities.RegularGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegularGroupRepository extends JpaRepository<RegularGroup, Long> {

    RegularGroup getByTournament(Long id);

}
