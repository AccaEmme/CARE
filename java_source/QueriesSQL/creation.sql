CREATE DATABASE IF NOT EXISTS CARE;

USE CARE;

CREATE TABLE IF NOT EXISTS `state_table` (
    id_state smallint NOT NULL PRIMARY KEY AUTO_INCREMENT,
    state varchar(10)
);

CREATE TABLE IF NOT EXISTS `role_table` (
    id_role smallint NOT NULL PRIMARY KEY AUTO_INCREMENT,
    usrRole varchar(20)
);

CREATE TABLE IF NOT EXISTS `Location`(
 id_location	smallint NOT NULL PRIMARY KEY AUTO_INCREMENT,
 country		char(30)	NOT NULL,
 region			char(30)	NOT NULL,
 province 		char(30) 	NOT NULL,
 city 			char(30) 	NOT NULL,
 street 		char(150) 	NOT NULL,
 streetNumber	char(5),
 ZipCode		char(5)
);


