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
@Table(name = "matches")
@ToString(exclude = "forecasts")
public class Match {
    public Match(MatchScore matchFinalResult, LocalDateTime matchDateTime, Long matchState, Team firstTeam, Team secondTeam, Tournament tournament) {
        this.matchFinalResult = matchFinalResult;
        this.matchDateTime = matchDateTime;
        this.matchState = matchState;
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
        this.tournament = tournament;
    }

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
    private Long matchState;

    @ManyToOne
    @JoinColumn(name = "first_team_id")
    private Team firstTeam;

    @ManyToOne
    @JoinColumn(name = "second_team_id")
    private Team secondTeam;

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    @OneToMany(mappedBy = "match")
    private Set<Forecast> forecasts  = new HashSet<>();
}
