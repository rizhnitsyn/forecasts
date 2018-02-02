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
@Table(name = "playoff_groups")
@PrimaryKeyJoinColumn(name = "group_id")
public class PlayoffGroup extends TournamentGroups {

    @Column(name = "is_extra_time_allowed")
    private boolean isExtraTimeAllowed;
}
