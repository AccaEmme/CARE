-- drop database care;

CREATE DATABASE IF NOT EXISTS  `CARE` 
DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE care;

CREATE TABLE IF NOT EXISTS `state_table` (
    id_state smallint NOT NULL PRIMARY KEY AUTO_INCREMENT,
    state varchar(10)
    -- 1 : presente
    -- 2 : trasferito
    -- 3 : scaduto
);

insert into `state_table` (id_state, state) values
    (null, 'presente'),
    (null, 'trasferito'),
    (null, 'scaduto');

select * from `state_table`;

CREATE TABLE IF NOT EXISTS `role_table` (
    id_role smallint NOT NULL PRIMARY KEY AUTO_INCREMENT,
    usrRole varchar(20)
    -- 1 : Officer
    -- 2 : StoreManager
    -- 3 : Administrator
);

insert into `role_table` (id_role, usrRole) values
    (null, 'Officer'),
    (null, 'StoreManager'),
    (null, 'Administrator');

select * from `role_table`;

-- tabella di comodo per la verifica dati di Residenza e Magazzino
CREATE TABLE IF NOT EXISTS `Location`(
 id_location	smallint NOT NULL PRIMARY KEY AUTO_INCREMENT,
 country		char(30)	NOT NULL,		-- Location
 region			char(30)	NOT NULL,		-- Location
 province 		char(30) 	NOT NULL,		-- Location
 city 			char(30) 	NOT NULL,		-- Location
 street 		char(150) 	NOT NULL,		-- Location
 streetNumber	char(5)		,				-- Location
 ZipCode		char(5)						-- Location
);
ALTER TABLE `Location` ADD PRIMARY KEY (`country`, `region`, `province`, `city`, `street`, `streetNumber`, `ZipCode`); -- The column referenced in a foreign key must be indexed.
insert into `Location` (`country`, `region`, `province`, `city`, `street`, `streetNumber`, `ZipCode`) values
    (
      'Italy', 					-- Location: country
      'Campania',  				-- Location: region
      'Avellino',  				-- Location: province
      'Avellino',  				-- Location: city
      'via Errico Carmelo',  	-- Location: street
      '2',  					-- Location: streetNumber
      '83100'  					-- Location: ZipCode
      );

CREATE TABLE IF NOT EXISTS `nodes_table`(
 id_node 		int 		NOT NULL PRIMARY KEY AUTO_INCREMENT,
 codStr			char(30)	NOT NULL UNIQUE,
 nodeName 		char(30) 	NOT NULL,
 
 country		char(30)	NOT NULL,
 region			char(30)	NOT NULL,
 province 		char(30) 	NOT NULL,
 city 			char(30) 	NOT NULL,
 street 		char(150) 	NOT NULL,
 streetNumber	char(5)		,
 ZipCode		char(5)		,
 
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
 
 endpoint	char(30)	NOT NULL, 	-- definisce la nostra routing table
 foreign key(`country`, `region`, `province`, `city`, `street`, `streetNumber`, `ZipCode`) references `Location`(`country`, `region`, `province`, `city`, `street`, `streetNumber`, `ZipCode`)
);
insert into `nodes_table` (id_node, codStr, nodeName, country, region, province, city, street, streetNumber, ZipCode, Apos_min, Apos_max, Aneg_min, Aneg_max, Bpos_min, Bpos_max, Bneg_min, Bneg_max, ABpos_min, ABpos_max, ABneg_min, ABneg_max, ZEROpos_min, ZEROpos_max, ZEROneg_min, ZEROneg_max, endpoint) values
    (
      null,						-- id_node
      '206',					-- codStr
      'Malzoni', 				-- nodeName
      
      'Italy', 					-- Location: country
      'Campania',  				-- Location: region
      'Avellino',  				-- Location: province
      'Avellino',  				-- Location: city
      'via Errico Carmelo',  	-- Location: street
      '2',  					-- Location: streetNumber
      '83100',  				-- Location: ZipCode
      
      null,  					-- Limits: Apos_min
      null,  					-- Limits: Apos_max
      null,  					-- Limits: Aneg_min
      null,  					-- Limits: Aneg_max
      null,  					-- Limits: Bpos_min
      null,  					-- Limits: Bpos_max
      null,  					-- Limits: Bneg_min
      null,  					-- Limits: Bneg_max
      null,  					-- Limits: ABpos_min
      null,  					-- Limits: ABpos_max
      null,  					-- Limits: ABneg_min
      null,  					-- Limits: ABneg_max
      null,  					-- Limits: ZEROpos_min
      null,  					-- Limits: ZEROpos_max
      null,  					-- Limits: ZEROneg_min
      null,  					-- Limits: ZEROneg_max
      
      "95.235.190.132"			-- routing table endpoint
	);

drop table IF EXISTS `BloodBags`;
CREATE TABLE IF NOT EXISTS `BloodBags`(
 idSerial char(32) NOT NULL PRIMARY KEY,
 bloodgroup 	char(7) NOT NULL,
 creationDate 	int NOT NULL,
 expiringDate	int NOT NULL,
 donatorCF		char(16),
 id_node		int,
 id_state 		smallint NOT NULL,
 note 			TEXT,
 foreign key (id_state) references `state_table`(id_state), -- referenzia la tabella degli stati
 foreign key (id_node) 	references `nodes_table`(id_node)    -- referenzia la tabella dei nodi
);
insert into `BloodBags` (idSerial, bloodGroup, creationDate, expiringDate, donatorCF, id_node, id_state, note) values
    ('IT-NA206000-Apos-20210517-0002', 'Apos', '0', '1', 'MGLHMNxxxxxxxxx', 1, 1, null ),
    ('IT-NA206000-Apos-20210518-0003', 'Aneg', '0', '1', 'MGLHMNxxxxxxxxx', 1, 2, null ),
    ('IT-NA206000-Apos-20210519-0004', 'Apos', '0', '1', 'MGLHMNxxxxxxxxx', 1, 3, null ),
    ('IT-NA206000-Apos-20210510-0005', 'Aneg', '0', '1', 'MGLHMNxxxxxxxxx', 1, 1, null );

drop table IF EXISTS `oldBloodBags`;1111
-- contiene le sacche eliminate, trasferite e usate.
CREATE TABLE IF NOT EXISTS `oldBloodBags`(
 idSerial char(32) NOT NULL PRIMARY KEY,
 bloodgroup 	char(7) NOT NULL,
 creationDate 	int NOT NULL,
 expiringDate	int NOT NULL,
 donatorCF		char(16),
 id_node		int,
 id_state 		smallint NOT NULL,
 note 			TEXT,
 foreign key (id_state) references `state_table`(id_state), -- referenzia la tabella degli stati
 foreign key (id_node) 	references `nodes_table`(id_node)    -- referenzia la tabella dei nodi
);

-- contiene le sacche eliminate, trasferite e usate.
CREATE TABLE IF NOT EXISTS `Users`(
 id_user 				int NOT NULL PRIMARY KEY AUTO_INCREMENT,
 username 				char(30) NOT NULL,
 hiddenpassword			char(32) NOT NULL,
 temppass				char(32),
 password_lastupdate	int NOT NULL,
 lastaccess				int NOT NULL,
 
 id_role				smallint	NOT NULL,
 
 country		char(30)	,		-- Residence
 region			char(30)	,		-- Residence
 province 		char(30) 	,		-- Residence
 city 			char(30) 	,		-- Residence
 street 		char(150) 	,		-- Residence
 streetNumber	char(5)		,		-- Residence
 ZipCode		char(5)		,		-- Residence

 foreign key(country, region, province, city) references `Location`(country, region, province, city),
 foreign key (id_role) references `role_table`(id_role) 	-- referenzia la tabella dei ruoli applicando un vincolo di chiave esterna
 -- Es. 1 | GCanfora | ea66ebb84ff0940dd72a35d12bcd8a72 | TempPassword@1 | 1620038942 | 1
);

insert into `Users` (iduser, username, password, lastaccess, idrole) values
    (NULL, 'GCanfora',  'ea66ebb84ff0940dd72a35d12bcd8a72', 1620038942, 1),
    (NULL, 'Peppe',     'ea66ebb84ff0940dd72a35d12bcd8a72', 1620038943, 2),
    (NULL, 'Antonello', 'ea66ebb84ff0940dd72a35d12bcd8a72', 1620038944, 3),
    (NULL, 'Luigino',   'ea66ebb84ff0940dd72a35d12bcd8a72', 1620038945, 3);

drop table IF EXISTS `Logger`;

CREATE TABLE IF NOT EXISTS `Logger`(
 id_logger int NOT NULL PRIMARY KEY AUTO_INCREMENT,
 whenHappened int NOT NULL,
 currentUser char(10) NOT NULL,
 operation char(20) NOT NULL,
 note_action char(250),
 bloodbag_serial char(32) ,
 note TEXT,
 foreign key (bloodbag_serial) references BloodBags(idSerial)

 -- es.
 -- 1620038801 | 206001 | LOGIN | Mario si è loggato nel sistema | null | null
 -- 1620038847 | 206001 | inserimento | Mario ha raccolto la sacca mettendola nel magazzino locale(206) | XXX_ID_SACCA_XXX | null
 -- 1620038848 | AutoSync | eliminazione | AutoSync ha prelevato la sacca dal magazzino locale per darla al magazzino centrale | XXX_ID_SACCA_XXX | null
 -- 1620038850 | 206001 | LOGOUT | Mario si è loggato nel sistema | null | null
 -- 1620038852 | ManualSync | eliminazione | Franco ha prelevato la sacca dal magazzino locale per darla al magazzino centrale | YYY_ID_SACCA_YYY | IP: 95.249.112.71 user_agent: Mozilla Firefox
);
insert into `Logger` (id, timestamp, user, operation, note_action, bloodbag_serial, note) values
    (null,1620038801,'206001','LOGIN','Mario si è loggato nel sistema ', null, null),
    (null,1620038847,'206001','inserimento','Mario ha raccolto la sacca mettendola nel magazzino locale(206)', 'IT-NA206000-Apos-20210517-0002', null),
    (null,1620038848,'AutoSync','eliminazione','AutoSync ha prelevato la sacca dal magazzino locale per darla al magazzino centrale', 'IT-NA206000-Apos-20210518-0003', null),
    (null,1620038847,'206001','inserimento','Mario ha raccolto la sacca mettendola nel magazzino locale(206)', 'IT-NA206000-Apos-20210519-0004', null),
    (null,1620038850,'206001','LOGOUT','Mario si è loggato nel sistema ', 'IT-NA206000-Apos-20210519-0004', null),
    (null,1620038852,'ManualSync','eliminazione','Franco ha prelevato la sacca dal magazzino locale per darla al magazzino centrale', 'IT-NA206000-Apos-20210519-0004', 'IP: 95.249.112.71 user_agent: Mozilla Firefox');


-- Non più buono:
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
 -- Es. 1 | Administrator | è l'utente amministratore | true | true | true | true | false | false | false | false | false
);

insert into `Roles` (idrole, rolename, roledescr, 
					getLog, createUser, editUser, 
					dropUser, setMasterNode, listBag, 
					addBag, removeBag, checkBag ) values
    (NULL,'Administrator', 'è l\'utente amministratore', true, true, true, true, false, false, false,false,false),
    (NULL,'Test1', 'ruolo di test 1', true, false, true, false, false, false, true,true,false),
    (NULL,'Test2', 'ruolo di test 2', true, true, false, true, true, false, false,false,false);
