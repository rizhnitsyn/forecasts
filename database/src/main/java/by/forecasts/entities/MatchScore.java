package by.forecasts.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Embeddable
public class MatchScore {

    @Column(name = "first_result")
    private Integer firstResult;

    @Column(name = "second_result")
    private Integer secondResult;
}
