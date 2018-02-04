package by.forecasts.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString(callSuper = true)
@Table(name = "regular_groups")
@PrimaryKeyJoinColumn(name = "group_id")
public class RegularGroup extends TournamentGroup {

    public RegularGroup(int teamsCountInGroup, int groupOutCount, int matchesCountBetweenTeams, Long groupNameId, Tournament tournament) {
        super(matchesCountBetweenTeams, groupNameId, tournament);
        this.teamsCountInGroup = teamsCountInGroup;
        this.groupOutCount = groupOutCount;
    }

    @Column(name = "teams_count", nullable = false)
    private int teamsCountInGroup;

    @Column(name = "group_out_count", nullable = false)
    private int groupOutCount;
}
