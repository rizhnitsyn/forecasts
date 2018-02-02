package by.forecasts.Entities;


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
@Table(name = "teams")
@ToString(exclude = {"homeMatches", "visitorMatches"})
public class Team {

    public Team(String teamName) {
        this.teamName = teamName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long id;

    @Column(name = "team_name", nullable = false, unique = true)
    private String teamName;

    @OneToMany(mappedBy = "firstTeam")
    private Set<Match> homeMatches = new HashSet<>();

    @OneToMany(mappedBy = "secondTeam")
    private Set<Match> visitorMatches = new HashSet<>();

}
