package by.forecasts.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "regular_matches")
@PrimaryKeyJoinColumn(name = "match_id")
public class RegularMatch extends Match {

    @Enumerated(EnumType.STRING)
    @Column(name = "group_name", nullable = false)
    private GroupNames groupName;
}
