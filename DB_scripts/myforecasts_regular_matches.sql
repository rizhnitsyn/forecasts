CREATE TABLE myforecasts.regular_matches
(
    match_id int(11) PRIMARY KEY NOT NULL,
    group_name varchar(50) NOT NULL,
    CONSTRAINT match_id_fk FOREIGN KEY (match_id) REFERENCES matches (match_id)
);