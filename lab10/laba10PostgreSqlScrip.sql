DROP TABLE IF EXISTS student;
CREATE TABLE student
(
    id     SERIAL PRIMARY KEY,
    name   TEXT    NOT NULL,
    series INTEGER NOT NULL,
    number INTEGER NOT NULL,
    UNIQUE (series, number)
);

DROP TABLE IF EXISTS subject;
CREATE TABLE subject
(
    id   SERIAL PRIMARY KEY,
    name text
);

DROP TABLE IF EXISTS progress;
CREATE TABLE progress
(
    id         SERIAL PRIMARY KEY,
    student_id INTEGER
        CONSTRAINT progress_fk REFERENCES student (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    subject_id INTEGER
        CONSTRAINT subject_fk REFERENCES student (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    mark       INTEGER
        CONSTRAINT valid_mark CHECK ( mark BETWEEN 2 AND 5 )
);

INSERT INTO student (name, series, number)
VALUES ('Misha', 123, 123);
INSERT INTO student (name, series, number)
VALUES ('Vova', 123, 124);
INSERT INTO student (name, series, number)
VALUES ('Nikita', 123, 125);
INSERT INTO student (name, series, number)
VALUES ('Andrey', 126, 126);
INSERT INTO student (name, series, number)
VALUES ('Valera', 127, 127);
INSERT INTO student (name, series, number)
VALUES ('Dima', 123, 128);
INSERT INTO student (name, series, number)
VALUES ('Bogdan', 123, 129);
INSERT INTO student (name, series, number)
VALUES ('Masksin', 126, 130);
INSERT INTO student (name, series, number)
VALUES ('Zakhar', 127, 131);


INSERT INTO subject
VALUES (DEFAULT, 'Технологии программирования');
INSERT INTO subject
VALUES (DEFAULT, 'Схемотехника');
INSERT INTO subject
VALUES (DEFAULT, 'Математика');

--Технологии программирования
INSERT INTO progress
VALUES (DEFAULT, 1, 1, 5);
INSERT INTO progress
VALUES (DEFAULT, 2, 1, 4);
INSERT INTO progress
VALUES (DEFAULT, 3, 1, 3);
INSERT INTO progress
VALUES (DEFAULT, 4, 1, 2);
INSERT INTO progress
VALUES (DEFAULT, 5, 1, 5);

--Схемотехника
INSERT INTO progress
VALUES (DEFAULT, 1, 2, 2);
INSERT INTO progress
VALUES (DEFAULT, 2, 2, 2);
INSERT INTO progress
VALUES (DEFAULT, 3, 2, 2);
INSERT INTO progress
VALUES (DEFAULT, 4, 2, 2);
INSERT INTO progress
VALUES (DEFAULT, 5, 2, 2);

--Математика
INSERT INTO progress
VALUES (DEFAULT, 1, 3, 3);
INSERT INTO progress
VALUES (DEFAULT, 2, 3, 3);
INSERT INTO progress
VALUES (DEFAULT, 3, 3, 2);
INSERT INTO progress
VALUES (DEFAULT, 4, 3, 2);
INSERT INTO progress
VALUES (DEFAULT, 5, 3, 2);

INSERT INTO progress
VALUES (DEFAULT, 6, 3, 5);
INSERT INTO progress
VALUES (DEFAULT, 7, 3, 5);
INSERT INTO progress
VALUES (DEFAULT, 8, 3, 5);
INSERT INTO progress
VALUES (DEFAULT, 9, 3, 5);



SELECT stud.name, subj.name, p.mark
FROM student AS stud
         JOIN progress AS p ON stud.id = p.student_id
         JOIN subject AS subj ON p.subject_id = subj.id;


--Вывести список студентов, сдавших определенный предмет, на оценку выше 3;
SELECT stud.name, subj.name, p.mark
FROM student AS stud
         JOIN progress AS p ON stud.id = p.student_id
         JOIN subject AS subj ON p.subject_id = subj.id
WHERE p.mark >= 3
  AND p.subject_id = 3;

--Посчитать средний бал по определенному предмету
SELECT subject.name, AVG(progress.mark)
FROM progress
         JOIN subject ON progress.subject_id = subject.id
GROUP BY subject.name;


--Посчитать средний балл по определенному студенту;
SELECT student.name, AVG(progress.mark)
FROM progress
         JOIN subject ON progress.subject_id = subject.id
         JOIN student ON student.id = progress.student_id
GROUP BY student.name;


--Найти три премета, которые сдали наибольшее количество студентов;
SELECT subj.name
FROM subject AS subj
         JOIN progress ON subj.id = progress.subject_id
WHERE (SELECT AVG(mark)
       FROM progress
                JOIN subject ON subj.id = progress.subject_id) >= 3
GROUP BY subj.name;

--Вывести всех отличников по математике
SELECT stud.name, subj.name, p.mark
FROM student AS stud
         JOIN progress AS p ON stud.id = p.student_id
         JOIN subject AS subj ON p.subject_id = subj.id
WHERE p.mark = 5 AND subj.id = 3 ORDER BY stud.name;
