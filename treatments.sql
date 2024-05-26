/* W23 Project Example Database
   2023 Barcomb
 
 Each time this file is executed, it will reset the database to the original state defined below.
 
 */

DROP DATABASE IF EXISTS ewr;
CREATE DATABASE ewr; 
\c ewr

DROP TABLE IF EXISTS ANIMALS;
CREATE TABLE ANIMALS (
	AnimalID		int not null PRIMARY KEY,
        AnimalNickname		varchar(25),
	AnimalSpecies		varchar(25)
);

INSERT INTO ANIMALS (AnimalID, AnimalNickname, AnimalSpecies) VALUES
(1, 'Loner', 'coyote'),
(2, 'Biter', 'coyote'),
(3, 'Bitter', 'coyote'),
(4, 'Pencil', 'coyote'),
(5, 'Eraser', 'coyote'),
(6, 'Annie, Oliver and Mowgli', 'fox'),
(7, 'Slinky', 'fox'),
(8, 'Spike', 'porcupine'),
(9, 'Javelin', 'porcupine'),
(10, 'Gatekeeper', 'porcupine'),
(11, 'Sunshine', 'porcupine'),
(12, 'Shadow', 'porcupine'),
(13, 'Boots', 'coyote'),
(14, 'Spin', 'coyote'),
(15, 'Spot', 'coyote');

DROP TABLE IF EXISTS TASKS;
CREATE TABLE TASKS (
	TaskID			int not null PRIMARY KEY,
	Description		varchar(50),
        Duration                int,
        MaxWindow               int
);

INSERT INTO TASKS (TaskID, Description, Duration, MaxWindow) VALUES
(1, 'Kit feeding', 30, 2),
(2, 'Rebandage leg wound', 20, 1),
(3, 'Apply burn ointment back', 10, 3),
(4, 'Administer antibiotics', 5, 1),
(5, 'Flush neck wound', 25, 1),
(6, 'Give fluid injection', 10, 1),
(7, 'Give vitamin injection', 5, 5),
(8, 'Mange treatment', 15, 4),
(9, 'Eyedrops', 25, 1),
(10, 'Inspect broken leg', 5, 2);

DROP TABLE IF EXISTS TREATMENTS;
CREATE TABLE TREATMENTS (
      	TreatmentID	int not null PRIMARY KEY,
	AnimalID	int not null,
	TaskID		int not null,
	StartHour	int not null
);

INSERT INTO TREATMENTS (TreatmentID, AnimalID, TaskID, StartHour) VALUES
(1,6, 1, 0),
(2,6, 1, 2),
(3, 6, 1, 4),
(4, 6, 1, 6),
(5, 6, 1, 8),
(6, 6, 1, 10),
(7, 6, 1, 12),
(8, 6, 1, 14),
(9, 6, 1, 16),
(10, 6, 1, 18),
(11, 6, 1, 20),
(12, 6, 1, 22),
(13, 1, 9, 22),
(14, 2, 10, 13),
(15, 2, 9, 13),
(16, 3, 7, 13),
(17, 4, 7, 13),
(18, 5, 7, 13),
(19, 7, 2, 19),
(20, 8, 5, 6),
(21, 8, 4, 6),
(22, 8, 4, 18),
(23, 9, 9, 22),
(24, 10, 7, 23),
(25, 11, 8, 23),
(26, 12, 3, 15),
(27, 13, 6, 22),
(28, 14, 10, 13),
(29, 14, 8, 13),
(30, 15, 8, 13);
