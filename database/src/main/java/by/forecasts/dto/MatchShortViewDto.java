package by.forecasts.dto;

import by.forecasts.entities.Forecast;
import by.forecasts.entities.MatchScore;
import by.forecasts.entities.MatchState;
import by.forecasts.entities.Team;
import by.forecasts.entities.Tournament;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MatchShortViewDto {

    private Long id;

    @NotNull(message = "errors.field.empty")
//    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime matchDateTime;
    private String matchDateTimeString;
    private Team firstTeam;
    private Team secondTeam;
    private MatchState matchState;
    private Tournament tournament;
    private Long groupId;
    private String error;
    private Forecast currentUserForecast;
    private int userPoints;
    private MatchScore matchScore;
    private int PageCount;
    private String groupName;
}
