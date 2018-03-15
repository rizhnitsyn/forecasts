package by.forecasts.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString(callSuper = true)
@Table(name = "regular_groups")
@PrimaryKeyJoinColumn(name = "group_id")
public class RegularGroup extends Group {

    public RegularGroup(Integer teamsCountInGroup, Integer groupOutCount, Integer matchesCountBetweenTeams, String groupName, Tournament tournament) {
        super(matchesCountBetweenTeams, groupName, tournament);
        this.teamsCountInGroup = teamsCountInGroup;
        this.groupOutCount = groupOutCount;
    }

    @NotNull(message = "errors.field.empty")
    @Min(value = 2, message = "errors.field.min2")
    @Column(name = "teams_count", nullable = false)
    private Integer teamsCountInGroup;

    @NotNull(message = "errors.field.empty")
    @Min(value = 1, message = "errors.field.min1")
    @Column(name = "group_out_count", nullable = false)
    private Integer groupOutCount;
}
