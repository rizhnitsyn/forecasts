package by.forecasts.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Embeddable
public class MatchScore {
    public MatchScore(Integer firstResult, Integer secondResult) {
        this.firstResult = firstResult;
        this.secondResult = secondResult;
    }

    @Column(name = "first_result")
    private Integer firstResult;

    @Column(name = "second_result")
    private Integer secondResult;
}
