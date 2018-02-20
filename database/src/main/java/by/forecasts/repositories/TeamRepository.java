package by.forecasts.repositories;

import by.forecasts.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {

}
