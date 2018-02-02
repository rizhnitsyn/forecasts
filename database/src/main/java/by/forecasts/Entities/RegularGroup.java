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
public class RegularGroup extends TournamentGroups {

    @Column(name = "teams_in_group", nullable = false)
    private int teamsInGroup;

    @Column(name = "group_out_count", nullable = false)
    private int groupOutCount;
}
