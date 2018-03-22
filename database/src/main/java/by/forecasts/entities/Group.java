package by.forecasts.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString(callSuper = true, exclude = {"teamsInGroup"})
@Table(name = "groups_in_tournament")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Group extends BaseEntity {

    public Group(Integer matchesCountBetweenTeams, String groupName, Tournament tournament) {
        this.matchesCountBetweenTeams = matchesCountBetweenTeams;
        this.groupName = groupName;
        this.tournament = tournament;
    }

    @NotNull(message = "errors.field.empty")
    @Min(value = 1, message = "errors.field.min1")
    @Column(name = "match_count_between_teams", nullable = false)
    private Integer matchesCountBetweenTeams;


    @NotEmpty(message = "errors.field.empty")
    @Column(name = "group_name", nullable = false)
    private String groupName;

    @ManyToOne
    @JoinColumn(name = "tournament_id", nullable = false)
    private Tournament tournament;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "teams_in_groups",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id"))
    private Set<Team> teamsInGroup = new HashSet<>();

    @Transient
    public void addTeam(Team team) {
        this.teamsInGroup.add(team);
    }

    @Transient public void delTeam(Team team) {
        this.teamsInGroup.remove(team);
    }
}
