package by.forecasts.repositories;

import by.forecasts.entities.PlayoffGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PlayoffGroupRepository extends JpaRepository<PlayoffGroup, Long> {
}
