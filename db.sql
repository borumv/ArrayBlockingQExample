BEGIN;
DROP TABLE IF EXISTS tasks CASCADE;
CREATE TABLE tasks (
                       id serial PRIMARY KEY,
                       nameTask text NOT NULL,
                       priority int2 NOT NULL CHECK(priority > 0 AND priority <= 10) DEFAULT 2);

INSERT INTO tasks (nameTask, priority)
VALUES ('task 1', 3), ('task 2', 7), ('task 3', 8), ('task 4', 3),
       ('task 5', 9), ('task 6', 4), ('task 7', 7), ('task 8', 4), ('task 9', 8),
       ('task 10', 3), ('task 11', 7), ('task 12', 8), ('task 13', 3),
       ('task 14', 9), ('task 15', 10), ('task 16', 7), ('task 17', 4), ('task 18', 8),
       ('task 19', 3), ('task 20', 7), ('task 21', 8), ('task 22', 3),
       ('task 23', 9), ('task 24', 4), ('task 25', 7), ('task 26', 4), ('task 27', 8),
       ('task 28', 3), ('task 29', 7), ('task 30', 8), ('task 31', 3),
       ('task 32', 9), ('task 33', 10), ('task 34', 7), ('task 35', 4), ('task 36', 8),
       ('task 37', 3), ('task 38', 7), ('task 39', 8), ('task 40', 3),
       ('task 41', 9), ('task 42', 4), ('task 43', 7), ('task 44', 4), ('task 45', 8);

COMMIT;