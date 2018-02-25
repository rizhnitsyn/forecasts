package by.forecasts.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ForecastFilter {
    Long tournamentId;
    Long userId;
    Long matchStateId;
    int recordsOnPage;
    int pageNo;
    int pagesCount;
}
