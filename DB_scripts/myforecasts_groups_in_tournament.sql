CREATE TABLE myforecasts.groups_in_tournament
(
    group_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    tournament_id int(11),
    match_count_between_teams int(11) NOT NULL,
    group_name_id int(11),
    CONSTRAINT groups_in_tournament_tournaments_tournament_id_fk FOREIGN KEY (tournament_id) REFERENCES tournaments (tournament_id),
    CONSTRAINT groups_in_tournament_group_names_group_name_id_fk FOREIGN KEY (group_name_id) REFERENCES group_names (group_name_id)
);
CREATE INDEX groups_in_tournament_group_names_group_name_id_fk ON myforecasts.groups_in_tournament (group_name_id);
CREATE INDEX groups_in_tournament_tournaments_tournament_id_fk ON myforecasts.groups_in_tournament (tournament_id);