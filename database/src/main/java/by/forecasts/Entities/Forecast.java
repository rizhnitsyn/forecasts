package by.forecasts.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "forecasts")
@ToString
public class Forecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "forecast_id")
    private Long id;

    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "firstResult", column = @Column(name = "first_team_forecast")),
            @AttributeOverride(name = "second_result", column = @Column(name = "second_team_forecast"))
    })
    private MatchScore matchForecast;

    @OneToMany(mappedBy = "forecasts")
    private User user;

    @OneToMany(mappedBy = "forecasts")
    private Match match;
}
