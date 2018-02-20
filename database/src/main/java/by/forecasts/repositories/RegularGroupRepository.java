package by.forecasts.repositories;

import by.forecasts.entities.RegularGroup;
import org.springframework.data.repository.CrudRepository;

public interface RegularGroupRepository extends CrudRepository<RegularGroup, Long> {

    RegularGroup getByTournament(Long id);

}
