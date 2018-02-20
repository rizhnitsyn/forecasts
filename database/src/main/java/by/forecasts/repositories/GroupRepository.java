package by.forecasts.repositories;

import by.forecasts.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
