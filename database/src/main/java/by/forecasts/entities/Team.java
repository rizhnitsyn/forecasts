package by.forecasts.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "teams")
@ToString(callSuper = true, exclude = {"visitorMatches", "homeMatches"})
public class Team extends BaseEntity {

    public Team(String teamName) {
        this.teamName = teamName;
    }

    @Column(name = "team_name", nullable = false, unique = true)
    private String teamName;

    @OneToMany(mappedBy = "firstTeam")
    private Set<Match> homeMatches = new HashSet<>();

    @OneToMany(mappedBy = "secondTeam")
    private Set<Match> visitorMatches = new HashSet<>();
}
