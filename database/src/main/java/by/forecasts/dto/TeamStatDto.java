package by.forecasts.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TeamStatDto {

    private Long id;
    private String teamName;
    private int win;
    private int lose;
    private int draw;
    private int diff;
    private int points;

}
