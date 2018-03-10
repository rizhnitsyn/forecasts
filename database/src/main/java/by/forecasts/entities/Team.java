package by.forecasts.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teams")
@ToString(callSuper = true, exclude = {"visitorMatches", "homeMatches", "groups"})
public class Team extends BaseEntity {

    public Team(String teamName) {
        super();
        this.teamName = teamName;
    }

    @Column(name = "team_name", nullable = false, unique = true)
    private String teamName;

    @OneToMany(mappedBy = "firstTeam")
    private Set<Match> homeMatches = new HashSet<>();

    @OneToMany(mappedBy = "secondTeam")
    private Set<Match> visitorMatches = new HashSet<>();

    @ManyToMany(mappedBy = "teamsInGroup")
    private Set<Group> groups = new HashSet<>();
}
