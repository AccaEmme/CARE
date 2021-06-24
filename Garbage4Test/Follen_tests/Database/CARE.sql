-- drop database care;

CREATE DATABASE IF NOT EXISTS  `CARE_F` 
DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE care_f;

CREATE TABLE IF NOT EXISTS `state_table` (
    id_state smallint NOT NULL PRIMARY KEY AUTO_INCREMENT,
    state varchar(10)
    -- 1 : presente
    -- 2 : trasferito
    -- 3 : scaduto
);

CREATE TABLE IF NOT EXISTS `role_table` (
    id_role smallint NOT NULL PRIMARY KEY AUTO_INCREMENT,
    usrRole varchar(20)
    -- 1 : Officer
    -- 2 : StoreManager
    -- 3 : Administrator
);

CREATE TABLE IF NOT EXISTS `locations_table`(
 id_location	int		 	PRIMARY KEY,
 country		char(30)	NOT NULL,
 region			char(30)	NOT NULL,
 province 		char(30) 	NOT NULL,
 city 			char(30) 	NOT NULL,
 street 		char(150) 	NOT NULL,
 streetNumber	char(5)	,
 ZipCode		char(5)
);


CREATE TABLE IF NOT EXISTS `groups_amount_table`(
	id_group_amount	int PRIMARY KEY,
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
	ZEROpos_min		smallint unsigned,	-- can be null
	ZEROpos_max		smallint unsigned,	-- can be null
	ZEROneg_min		smallint unsigned,	-- can be null
	ZEROneg_max		smallint unsigned
);

CREATE TABLE IF NOT EXISTS `nodes_table`(
	id_node 		int 		PRIMARY KEY AUTO_INCREMENT,
	codStr			char(30)	NOT NULL UNIQUE,
	nodeName 		char(30) 	NOT NULL,
    endpoint	char(30)	NOT NULL, 	-- definisce la nostra routing table
	
    location		int,
    group_amount	int,
    
    FOREIGN KEY 	(location)
		REFERENCES	locations_table	(id_location),
	FOREIGN KEY 	(group_amount)
		REFERENCES	groups_amount_table	(id_group_amount)
);

CREATE TABLE IF NOT EXISTS `BloodBags`(
	idSerial 		char(32) 	NOT NULL PRIMARY KEY,
	bloodgroup 		char(7) 	NOT NULL,
	creationDate 	int 		NOT NULL,
	expiringDate	int 		NOT NULL,
	donatorCF		char(16),
    note 			TEXT,
    
    
	id_node			int,
	id_state 		smallint 	NOT NULL,
	
	foreign key (id_state) references `state_table`(id_state), -- referenzia la tabella degli stati
	foreign key (id_node) 	references `nodes_table`(id_node)    -- referenzia la tabella dei nodi
);


CREATE TABLE IF NOT EXISTS `logs_table`(
	id_logger 			int 		NOT NULL PRIMARY KEY AUTO_INCREMENT,
	whenHappened 		int 		NOT NULL,
	currentUser 		char(10) 	NOT NULL,
	operation 			char(20) 	NOT NULL,
	note_action 		char(250),
	note 				TEXT,
        
        
	bloodbag_serial 	char(32) ,

	foreign key (bloodbag_serial) references BloodBags(idSerial)

	-- es.
	-- 1620038801 | 206001 | LOGIN | Mario si è loggato nel sistema | null | null
	-- 1620038847 | 206001 | inserimento | Mario ha raccolto la sacca mettendola nel magazzino locale(206) | XXX_ID_SACCA_XXX | null
	-- 1620038848 | AutoSync | eliminazione | AutoSync ha prelevato la sacca dal magazzino locale per darla al magazzino centrale | XXX_ID_SACCA_XXX | null
	-- 1620038850 | 206001 | LOGOUT | Mario si è loggato nel sistema | null | null
	-- 1620038852 | ManualSync | eliminazione | Franco ha prelevato la sacca dal magazzino locale per darla al magazzino centrale | YYY_ID_SACCA_YYY | IP: 95.249.112.71 user_agent: Mozilla Firefox
);





