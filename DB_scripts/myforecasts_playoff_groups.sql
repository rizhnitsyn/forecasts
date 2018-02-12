CREATE TABLE myforecasts.playoff_groups
(
    group_id int(11) PRIMARY KEY NOT NULL,
    is_extra_time_allowed tinyint(1) NOT NULL,
    CONSTRAINT playoff_groups_groups_in_tournament_group_id_fk FOREIGN KEY (group_id) REFERENCES groups_in_tournament (id)
);