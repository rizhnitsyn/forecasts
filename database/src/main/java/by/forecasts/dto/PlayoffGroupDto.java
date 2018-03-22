package by.forecasts.dto;

import by.forecasts.entities.Group;
import by.forecasts.entities.Match;
import by.forecasts.entities.Team;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PlayoffGroupDto {

    private Long id;
    private String groupName;
    private List<Team> teams;
    private List<Match> matches;
    private Set<Team> teamsInGroup;

    public PlayoffGroupDto(Group group) {
        this.id = group.getId();
        this.groupName = group.getGroupName();
        this.teamsInGroup = group.getTeamsInGroup();
    }
}
