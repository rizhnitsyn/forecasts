package by.forecasts.dto;

import by.forecasts.entities.Match;
import by.forecasts.entities.MatchScore;
import by.forecasts.entities.MatchState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MatchHardViewDto {

    private Long id;
    private MatchScore matchScore;
    private LocalDateTime matchDateTime;
    private MatchState matchState;
    private String firstTeam;
    private String secondTeam;
    private String tournamentName;
    private int forecastsCount;
    private MatchScore currentUserForecast;
    private int firstTeamWinCount;
    private int secondTeamWinCount;
    private int drawCount;
    private int guessedWinnersCount;
    private int guessedDiffInResultsCount;
    private int guessedResultsCount;
    private int currentUserPoints;
    private String strMatchDateTime;
    private Long tournamentId;
    private boolean IsActiveForForecasts;

    public MatchHardViewDto(Match match) {
        this.id = match.getId();
        this.matchScore = match.getMatchFinalResult();
        this.matchDateTime = match.getMatchDateTime();
        this.firstTeam = match.getFirstTeam().getTeamName();
        this.secondTeam = match.getSecondTeam().getTeamName();
        this.tournamentName = match.getTournament().getName();
        this.tournamentId = match.getTournament().getId();
        this.matchState = match.getMatchState();
    }
}
