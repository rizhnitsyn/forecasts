package by.forecasts.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Embeddable
public class MatchScore {

    @NotNull(message = "errors.field.empty")
    @Min(value = 0, message = "errors.field.min0")
    @Column(name = "first_result")
    private Integer firstResult;

    @NotNull(message = "errors.field.empty")
    @Min(value = 0, message = "errors.field.min0")
    @Column(name = "second_result")
    private Integer secondResult;
}
