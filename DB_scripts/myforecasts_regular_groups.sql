CREATE TABLE myforecasts.regular_groups
(
    group_id int(11) PRIMARY KEY NOT NULL,
    teams_in_group int(11) NOT NULL,
    group_out_count int(11) NOT NULL,
    CONSTRAINT regular_groups_groups_in_tournament_group_id_fk FOREIGN KEY (group_id) REFERENCES groups_in_tournament (group_id)
);