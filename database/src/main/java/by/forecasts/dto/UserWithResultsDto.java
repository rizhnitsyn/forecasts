package by.forecasts.dto;

import by.forecasts.entities.Forecast;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserWithResultsDto {
    private Long userId;
    private String firstName;
    private String secondName;
    private List<Forecast> forecasts;
    private int totalPoints;
    private int guessedResultCount;
    private int guessedWinnersCount;
    private int guessedDiffInResultsCount;
    private int guessedDrawCount;
}
