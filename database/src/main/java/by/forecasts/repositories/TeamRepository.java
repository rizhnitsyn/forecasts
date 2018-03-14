package by.forecasts.repositories;

import by.forecasts.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    List<Team> findAllByIdNotIn(List<Long> teams);

    List<Team> findAllByGroupsIdIn(List<Long> groups);

    List<Team> findAllByGroupsId(Long groupId);
}
