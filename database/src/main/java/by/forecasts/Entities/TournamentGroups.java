package by.forecasts.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "groups_in_tournament")
@Inheritance(strategy = InheritanceType.JOINED)
public class TournamentGroups {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Long groupId;

    @Column(name = "match_count_between_teams", nullable = false)
    private int matchesCountBetweenTeams;

    @Column(name = "group_name_id")
    private Long groupNameId;

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    @ManyToMany
    @JoinTable(name = "team_in_groups",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id"))
    private Set<Team> teamsInGroup = new HashSet<>();

}
