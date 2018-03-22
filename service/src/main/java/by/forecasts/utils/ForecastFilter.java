package by.forecasts.utils;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ForecastFilter {
    private Long tournamentId;
    private Long userId;
    private Long matchStateId;
    private int recordsOnPage;
    private int pageNo;
    private int pagesCount;
}
