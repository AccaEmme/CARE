DROP DATABASE IF EXISTS care;

CREATE DATABASE IF NOT EXISTS  `CARE` 
DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE care;

DROP TABLE IF EXISTS `state_table`;
CREATE TABLE IF NOT EXISTS `state_table` (
    id_state smallint NOT NULL PRIMARY KEY AUTO_INCREMENT,
    state varchar(10) UNIQUE
    -- 1 : presente
    -- 2 : trasferito
    -- 3 : scaduto
);

INSERT INTO `state_table` (id_state, state) VALUES
    (null, 'presente'),
    (null, 'trasferito'),
    (null, 'scaduto');

select* from `state_table`;
drop table IF EXISTS `BloodBags`;



CREATE TABLE IF NOT EXISTS `BloodBags`(
 serial char(33) NOT NULL PRIMARY KEY,
 Rh char(7) NOT NULL,
 creation int NOT NULL,
 expiring int NOT NULL,
 donatorCF char(16) NOT NULL,
 note TEXT,
 id_state smallint NOT NULL,

 foreign key (id_state) references `state_table`(id_state)-- referenzia la tabella degli stati
);
insert into `BloodBags` (serial, Rh, creation, expiring, id_state, note) values
    ('IT-NA206000-Apos-20210517-0002', 'Apos', '0', '1', 1, null ),
    ('IT-NA206000-Apos-20210518-0003', 'Aneg', '0', '1', 2, null ),
    ('IT-NA206000-Apos-20210519-0004', 'Apos', '0', '1', 3, null ),
    ('IT-NA206000-Apos-20210510-0005', 'Aneg', '0', '1', 1, null );

drop table IF EXISTS `Logger`;

CREATE TABLE IF NOT EXISTS `Logger`(
 id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
 timestamp int NOT NULL,
 user char(10) NOT NULL,
 operation char(20) NOT NULL,
 note_action char(250),
 bloodbag_serial char(32) ,
 foreign key (bloodbag_serial) references BloodBags(serial),
 note TEXT

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

DROP TABLE IF EXISTS `Users`;
CREATE TABLE IF NOT EXISTS `Users` (
 iduser int NOT NULL PRIMARY KEY AUTO_INCREMENT,
 username char(20) NOT NULL,
 password char(32) NOT NULL, -- it contains MD5() password
 lastaccess int NOT NULL,
 idrole int NOT NULL,
 
 foreign key (idrole) references Roles(idrole)
 -- Es. 1 | GCanfora | ea66ebb84ff0940dd72a35d12bcd8a72 | 1620038942 | 1
);

insert into `Users` (iduser, username, password, lastaccess, idrole) values
    (NULL, 'GCanfora',  'ea66ebb84ff0940dd72a35d12bcd8a72', 1620038942, 1),
    (NULL, 'Peppe',     'ea66ebb84ff0940dd72a35d12bcd8a72', 1620038943, 2),
    (NULL, 'Antonello', 'ea66ebb84ff0940dd72a35d12bcd8a72', 1620038944, 3),
    (NULL, 'Luigino',   'ea66ebb84ff0940dd72a35d12bcd8a72', 1620038945, 3);