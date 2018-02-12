CREATE TABLE myforecasts.match_comments
(
    comment_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    comment_txt text NOT NULL,
    match_id int(11) NOT NULL,
    user_id int(11) NOT NULL,
    comment_datetime datetime NOT NULL,
    CONSTRAINT MATCH_COMMENTS_fk0 FOREIGN KEY (match_id) REFERENCES matches (id),
    CONSTRAINT MATCH_COMMENTS_fk1 FOREIGN KEY (user_id) REFERENCES users (id)
);
CREATE INDEX MATCH_COMMENTS_fk0 ON myforecasts.match_comments (match_id);
CREATE INDEX MATCH_COMMENTS_fk1 ON myforecasts.match_comments (user_id);