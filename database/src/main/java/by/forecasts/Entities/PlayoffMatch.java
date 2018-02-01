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
@ToString(callSuper = true)
@Table(name = "playoff_matches")
@PrimaryKeyJoinColumn(name = "match_id")
public class PlayoffMatch extends Match {

    @Enumerated(EnumType.STRING)
    @Column(name = "match_stage", nullable = false)
    private MatchStage matchStage;
}
