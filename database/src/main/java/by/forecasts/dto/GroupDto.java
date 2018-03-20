package by.forecasts.dto;

import by.forecasts.entities.Group;
import by.forecasts.entities.Team;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class GroupDto {

    private Long id;
    private String groupName;
    private Set<Team> teamsInGroup;
    private List<TeamStatDto> teams;

    public GroupDto(Group group) {
        this.id = group.getId();
        this.groupName = group.getGroupName();
        this.teamsInGroup = group.getTeamsInGroup();
    }
}
