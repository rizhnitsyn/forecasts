package by.forecasts.dto;

import by.forecasts.entities.MatchState;
import by.forecasts.entities.Team;
import by.forecasts.entities.Tournament;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MatchShortViewDto {

    private Long id;
    private LocalDateTime matchDateTime;

    @NotEmpty(message = "errors.field.empty")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}")
    private String matchDateTimeString;
    private Team firstTeam;
    private Team secondTeam;
    private MatchState matchState;
    private Tournament tournament;
    private Long groupId;
    private String error;
}
