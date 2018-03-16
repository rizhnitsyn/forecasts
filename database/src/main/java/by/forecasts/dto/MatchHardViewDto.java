package by.forecasts.dto;

import by.forecasts.entities.Forecast;
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
    private Integer firstTeamResult;
    private Integer secondTeamResult;
    private LocalDateTime matchDateTime;
    private String matchState;
    private String matchType;
    private String firstTeam;
    private String secondTeam;
    private String tournamentName;
    private int forecastsCount;
    private Forecast currentUserForecast;
    private int firstTeamWinCount;
    private int secondTeamWinCount;
    private int drawCount;
    private int guessedWinnersCount;
    private int guessedDiffInResultsCount;
    private int guessedResultsCount;
    private int currentUserPoints;
    private String strMatchDateTime;
    private Long tournamentId;
}
