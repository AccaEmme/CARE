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

CREATE TABLE IF NOT EXISTS `nodes_table`(
 id_node 		int 		NOT NULL PRIMARY KEY AUTO_INCREMENT,
 codStr			char(30)	NOT NULL UNIQUE,
 nodeName 		char(30) 	NOT NULL,

 country		char(30)	NOT NULL UNIQUE,
 region			char(30)	NOT NULL UNIQUE,
 province 		char(30) 	NOT NULL UNIQUE,
 city 			char(30) 	NOT NULL UNIQUE,
 street 		char(150) 	NOT NULL UNIQUE,
 streetNumber	char(5)		NOT NULL UNIQUE,
 ZipCode		char(5)		NOT NULL UNIQUE,

 Apos_min		smallint unsigned,	-- can be null
 Apos_max		smallint unsigned,	-- can be null
 Aneg_min		smallint unsigned,	-- can be null
 Aneg_max		smallint unsigned,	-- can be null
 Bpos_min		smallint unsigned,	-- can be null
 Bpos_max		smallint unsigned,	-- can be null
 Bneg_min		smallint unsigned,	-- can be null
 Bneg_max		smallint unsigned,	-- can be null
 ABpos_min		smallint unsigned,	-- can be null
 ABpos_max		smallint unsigned,	-- can be null
 ABneg_min		smallint unsigned,	-- can be null
 ABneg_max		smallint unsigned,	-- can be null
 ZEROpos_min	smallint unsigned,	-- can be null
 ZEROpos_max	smallint unsigned,	-- can be null
 ZEROneg_min	smallint unsigned,	-- can be null
 ZEROneg_max	smallint unsigned,	-- can be null

 endpoint	char(30)	NOT NULL,
 foreign key(`country`, `region`, `province`, `city`, `street`, `streetNumber`, `ZipCode`) references `Location`(`country`, `region`, `province`, `city`, `street`, `streetNumber`, `ZipCode`)
);

CREATE TABLE IF NOT EXISTS `BloodBags`(
 idSerial char(32) NOT NULL PRIMARY KEY,
 bloodgroup 	char(7) NOT NULL,
 creationDate 	int NOT NULL,
 expiringDate	int NOT NULL,
 donatorCF		char(16),
 id_node		int,
 id_state 		smallint NOT NULL,
 note 			TEXT,
 foreign key (id_state) references `state_table`(id_state),
 foreign key (id_node) 	references `nodes_table`(id_node)
);

CREATE TABLE IF NOT EXISTS `oldBloodBags`(
 idSerial char(32) NOT NULL PRIMARY KEY,
 bloodgroup 	char(7) NOT NULL,
 creationDate 	int NOT NULL,
 expiringDate	int NOT NULL,
 donatorCF		char(16),
 id_node		int,
 id_state 		smallint NOT NULL,
 note 			TEXT,
 foreign key (id_state) references `state_table`(id_state),
 foreign key (id_node) 	references `nodes_table`(id_node)
);

CREATE TABLE IF NOT EXISTS `Users`(
 id_user 				int NOT NULL PRIMARY KEY AUTO_INCREMENT,
 username 				char(30) NOT NULL,
 hiddenpassword			char(32) NOT NULL,
 temppass				char(32),
 password_lastupdate	int NOT NULL,
 lastaccess				int NOT NULL,
 id_role				smallint	NOT NULL,
 country		char(30)	,
 region			char(30)	,
 province 		char(30) 	,
 city 			char(30) 	,
 street 		char(150) 	,
 streetNumber	char(5)		,
 ZipCode		char(5)		,
 foreign key(country, region, province, city) references `Location`(country, region, province, city),
 foreign key (id_role) references `role_table`(id_role)
);

CREATE TABLE IF NOT EXISTS `Logger`(
 id_logger int NOT NULL PRIMARY KEY AUTO_INCREMENT,
 whenHappened int NOT NULL,
 currentUser char(10) NOT NULL,
 operation char(20) NOT NULL,
 note_action char(250),
 bloodbag_serial char(32) ,
 note TEXT,
 foreign key (bloodbag_serial) references BloodBags(idSerial)
);

CREATE TABLE IF NOT EXISTS `Roles` (
 idrole int NOT NULL PRIMARY KEY AUTO_INCREMENT,
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
