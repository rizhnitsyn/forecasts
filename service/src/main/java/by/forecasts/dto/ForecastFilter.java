package by.forecasts.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ForecastFilter {
    Long tournamentId;
    Long userId;
    Long matchStateId;
    int recordsCnt;
    int PageNo;

}
