package by.forecasts.entities;


import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@Embeddable
public class MatchScore {

    @Column(name = "first_result")
    private Integer firstResult;

    @Column(name = "second_result")
    private Integer secondResult;
}
