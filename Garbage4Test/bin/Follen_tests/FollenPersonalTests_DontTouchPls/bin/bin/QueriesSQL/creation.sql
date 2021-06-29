CREATE DATABASE CARE;

USE CARE;

CREATE TABLE Node_BloodBags(
 serial char(32) NOT NULL PRIMARY KEY,
 Rh char(7) NOT NULL,
 expiring int NOT NULL,
 state boolean NOT NULL, 	
 note TEXT
);

CREATE TABLE Local_BloodBags(
 serial char(32) NOT NULL PRIMARY KEY,
 Rh char(7) NOT NULL,
 expiring int NOT NULL,
 state boolean NOT NULL, 
 note TEXT
);

CREATE TABLE Nodes_BloodBags(
 serial char(32) NOT NULL PRIMARY KEY,
 id_node char(3) NOT NULL,
 Rh char(7) NOT NULL,
 expiring int NOT NULL,
 state boolean NOT NULL,
 note TEXT
);

CREATE TABLE Logger(
 id int NOT NULL PRIMARY KEY,
 timestamp int NOT NULL,
 user char(10) NOT NULL,
 operation char(10) NOT NULL,
 note_action char(250),
 bloodbag_serial char(32),
 note TEXT
);

CREATE TABLE Users (
 iduser int NOT NULL PRIMARY KEY,
 username char(20) NOT NULL,
 password char(20) NOT NULL,
 lastaccess int NOT NULL,
 idrole int NOT NULL
);

CREATE TABLE Roles (
 idrole int NOT NULL PRIMARY KEY,
 rolename char(20) NOT NULL,
 roledescr char(200),
 getLog boolean NOT NULL,
 createUser boolean NOT NULL,
 editUser boolean NOT NULL,
 dropUser boolean NOT NULL,
 setMasterNode boolean NOT NULL,
 listBag boolean NOT NULL,
 addBag boolean NOT NULL,
 removeBag boolean NOT NULL,
 checkBag boolean NOT NULL
);

