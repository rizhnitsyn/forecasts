CREATE TABLE myforecasts.playoff_matches
(
    match_id int(11) PRIMARY KEY NOT NULL,
    match_stage varchar(45) NOT NULL,
    CONSTRAINT match_id FOREIGN KEY (match_id) REFERENCES matches (match_id)
);