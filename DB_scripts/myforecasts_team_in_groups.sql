CREATE TABLE myforecasts.team_in_groups
(
    group_id int(11) NOT NULL,
    team_id int(11) NOT NULL,
    CONSTRAINT group_id_fk FOREIGN KEY (group_id) REFERENCES groups_in_tournament (group_id),
    CONSTRAINT team_id_fk FOREIGN KEY (team_id) REFERENCES teams (team_id)
);
CREATE INDEX group_id_fk_idx ON myforecasts.team_in_groups (group_id);
CREATE INDEX team_id_fk_idx ON myforecasts.team_in_groups (team_id);