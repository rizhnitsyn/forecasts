package by.forecasts.dto;

import by.forecasts.entities.MatchState;
import by.forecasts.entities.Team;
import by.forecasts.entities.Tournament;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MatchShortViewDto {

    private Long id;
    private LocalDateTime matchDateTime;
    private String matchDateTimeString;
    private Team firstTeam;
    private Team secondTeam;
    private MatchState matchState;
    private Tournament tournament;
    private Long groupId;
    private String error;
}
