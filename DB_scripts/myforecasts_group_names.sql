CREATE TABLE myforecasts.group_names
(
    group_name_id int(11) PRIMARY KEY NOT NULL,
    group_name varchar(45) NOT NULL,
    group_type_id int(11) NOT NULL,
    CONSTRAINT group_names__fk FOREIGN KEY (group_type_id) REFERENCES group_types (group_type_id)
);
CREATE UNIQUE INDEX group_type_name_UNIQUE ON myforecasts.group_names (group_name);
CREATE INDEX group_names__fk ON myforecasts.group_names (group_type_id);
INSERT INTO myforecasts.group_names (group_name_id, group_name, group_type_id) VALUES (1, 'Группа А', 1);
INSERT INTO myforecasts.group_names (group_name_id, group_name, group_type_id) VALUES (2, 'Группа B', 1);
INSERT INTO myforecasts.group_names (group_name_id, group_name, group_type_id) VALUES (3, 'Группа C', 1);
INSERT INTO myforecasts.group_names (group_name_id, group_name, group_type_id) VALUES (4, 'Группа D', 1);
INSERT INTO myforecasts.group_names (group_name_id, group_name, group_type_id) VALUES (5, 'Группа E', 1);
INSERT INTO myforecasts.group_names (group_name_id, group_name, group_type_id) VALUES (6, 'Группа F', 1);
INSERT INTO myforecasts.group_names (group_name_id, group_name, group_type_id) VALUES (7, 'Группа G', 1);
INSERT INTO myforecasts.group_names (group_name_id, group_name, group_type_id) VALUES (8, 'Группа I', 1);
INSERT INTO myforecasts.group_names (group_name_id, group_name, group_type_id) VALUES (9, '1\\8 финала', 2);
INSERT INTO myforecasts.group_names (group_name_id, group_name, group_type_id) VALUES (10, '1\\4 финала', 2);
INSERT INTO myforecasts.group_names (group_name_id, group_name, group_type_id) VALUES (11, '1\\2 финала', 2);
INSERT INTO myforecasts.group_names (group_name_id, group_name, group_type_id) VALUES (12, 'Финал', 2);