package by.forecasts.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
@Table(name = "matches")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    private Long matchId;

    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "firstResult", column = @Column(name = "first_team_result")),
            @AttributeOverride(name = "secondResult", column = @Column(name = "second_team_result"))
    })
    private MatchScore matchFinalResult;

    @Column(name = "match_datetime", nullable = false)
    private LocalDateTime matchDateTime;

    @Column(name = "match_state_id", nullable = false)
    private int matchState;

    @OneToMany
    @JoinColumn(name = "team_id")
    private Team firstTeam;

    @OneToMany
    @JoinColumn(name = "team_id")
    private Team secondTeam;

    @OneToMany(mappedBy = "matches")
    private Tournament tournament;

    @ManyToOne()
    @JoinColumn(name = "forecast_id")
    private Set<Forecast> forecasts  = new HashSet<>();
}
