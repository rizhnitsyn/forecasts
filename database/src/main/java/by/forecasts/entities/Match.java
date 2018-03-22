package by.forecasts.entities;

import by.forecasts.dto.MatchShortViewDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "matches")
@ToString(exclude = "forecasts", callSuper = true)
public class Match extends BaseEntity {

    public Match(LocalDateTime matchDateTime, MatchState matchState, Team firstTeam, Team secondTeam, Tournament tournament) {
        this.matchDateTime = matchDateTime;
        this.matchState = matchState;
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
        this.tournament = tournament;
    }

    public Match(MatchShortViewDto match) {
        this.matchDateTime = match.getMatchDateTime();
        this.matchState = match.getMatchState();
        this.firstTeam = match.getFirstTeam();
        this.secondTeam = match.getSecondTeam();
        this.tournament = match.getTournament();
    }

    public Match(MatchScore matchFinalResult, LocalDateTime matchDateTime, MatchState matchState, Team firstTeam, Team secondTeam, Tournament tournament) {
        this.matchFinalResult = matchFinalResult;
        this.matchDateTime = matchDateTime;
        this.matchState = matchState;

        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
        this.tournament = tournament;
    }

    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "firstResult", column = @Column(name = "first_team_result")),
            @AttributeOverride(name = "secondResult", column = @Column(name = "second_team_result"))
    })
    private MatchScore matchFinalResult;

    @Column(name = "match_datetime", nullable = false)
    private LocalDateTime matchDateTime;

    @ManyToOne
    @JoinColumn(name = "match_state_id", nullable = false)
    private MatchState matchState;

    @ManyToOne
    @JoinColumn(name = "first_team_id", nullable = false)
    private Team firstTeam;

    @ManyToOne
    @JoinColumn(name = "second_team_id", nullable = false)
    private Team secondTeam;

    @ManyToOne
    @JoinColumn(name = "tournament_id", nullable = false)
    private Tournament tournament;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @OneToMany(mappedBy = "match")
    private Set<Forecast> forecasts  = new HashSet<>();

    public void addForecast(Forecast forecast) {
        forecasts.add(forecast);
    }
}
