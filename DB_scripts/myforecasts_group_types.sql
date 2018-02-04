CREATE TABLE myforecasts.group_types
(
    group_type_id int(11) PRIMARY KEY NOT NULL,
    group_type_name varchar(30)
);
INSERT INTO myforecasts.group_types (group_type_id, group_type_name) VALUES (1, 'Групповая стадия');
INSERT INTO myforecasts.group_types (group_type_id, group_type_name) VALUES (2, 'Плейофф');