package by.forecasts.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupCreateDto {

    private Long id;
    private int matchesCount;
    private String name;
    private boolean extraTime;
    private int teamCount;
    private int groupOutCount;
}
