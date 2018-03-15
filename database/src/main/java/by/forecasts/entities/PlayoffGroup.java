package by.forecasts.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString(callSuper = true)
@Table(name = "playoff_groups")
@PrimaryKeyJoinColumn(name = "group_id")
public class PlayoffGroup extends Group {

    public PlayoffGroup(Integer matchesCountBetweenTeams, String groupName, Tournament tournament, boolean isExtraTimeAllowed) {
        super(matchesCountBetweenTeams, groupName, tournament);
        this.isExtraTimeAllowed = isExtraTimeAllowed;
    }

    @Column(name = "is_extra_time_allowed", nullable = false)
    @NotNull(message = "errors.field.empty")
    private boolean isExtraTimeAllowed;
}
