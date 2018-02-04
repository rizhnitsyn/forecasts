package by.forecasts.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
@Table(name = "groups_in_tournament")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class TournamentGroup {

    public TournamentGroup(int matchesCountBetweenTeams, Long groupNameId, Tournament tournament) {
        this.matchesCountBetweenTeams = matchesCountBetweenTeams;
        this.groupNameId = groupNameId;
        this.tournament = tournament;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Long groupId;

    @Column(name = "match_count_between_teams", nullable = false)
    private int matchesCountBetweenTeams;

    @Column(name = "group_name_id", nullable = false)
    private Long groupNameId;

    @ManyToOne
    @JoinColumn(name = "tournament_id", nullable = false)
    private Tournament tournament;

    @ManyToMany
    @JoinTable(name = "teams_in_groups",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id"))
    private Set<Team> teamsInGroup = new HashSet<>();

}
