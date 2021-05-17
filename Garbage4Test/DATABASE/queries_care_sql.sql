use care;

CREATE TABLE state_table(
	id_state smallint NOT NULL PRIMARY KEY,
    state varchar(10)
    -- 0 : presente
    -- 1 : trasferito
    -- 2 : scaduto
);

insert into state_table(id_state, state) values
	(0, 'presente'),
    (1, 'trasferito'),
    (2, 'scaduto');

drop table Node_BloodBags;

CREATE TABLE Node_BloodBags(
 serial char(32) NOT NULL PRIMARY KEY,
 Rh char(7) NOT NULL,
 creation int NOT NULL,
 expiring int NOT NULL,
 id_state smallint,
 foreign key (id_state) references state_table(id_state),-- referenzia la tabella degli stati
 note TEXT
);
insert into Node_BloodBags(serial, Rh, creation, expiring, id_state, note) values
	('IT-NA206000-Apos-20210517-0002', 'Apos', '0', '1', 0, null ),
    ('IT-NA206000-Apos-20210518-0003', 'Aneg', '0', '1', 1, null ),
    ('IT-NA206000-Apos-20210519-0004', 'Apos', '0', '1', 2, null ),
    ('IT-NA206000-Apos-20210510-0005', 'Aneg', '0', '1', 0, null );
drop table Logger;

CREATE TABLE Logger(
 id int NOT NULL PRIMARY KEY auto_increment,
 timestamp int NOT NULL,
 user char(10) NOT NULL,
 operation char(20) NOT NULL,
 note_action char(250),
 bloodbag_serial char(32) ,
 foreign key (bloodbag_serial) references Node_BloodBags(serial),
 note TEXT

 -- es.
 -- 1620038801 | 206001 | LOGIN | Mario si è loggato nel sistema | null | null
 -- 1620038847 | 206001 | inserimento | Mario ha raccolto la sacca mettendola nel magazzino locale(206) | XXX_ID_SACCA_XXX | null
 -- 1620038848 | AutoSync | eliminazione | AutoSync ha prelevato la sacca dal magazzino locale per darla al magazzino centrale | XXX_ID_SACCA_XXX | null
 -- 1620038850 | 206001 | LOGOUT | Mario si è loggato nel sistema | null | null
 -- 1620038852 | ManualSync | eliminazione | Franco ha prelevato la sacca dal magazzino locale per darla al magazzino centrale | YYY_ID_SACCA_YYY | IP: 95.249.112.71 user_agent: Mozilla Firefox
);
insert into Logger(id, timestamp, user, operation, note_action, bloodbag_serial, note) values
	(null,1620038801,'206001','LOGIN','Mario si è loggato nel sistema ', null, null),
    (null,1620038847,'206001','inserimento','Mario ha raccolto la sacca mettendola nel magazzino locale(206)', 'IT-NA206000-Apos-20210517-0002', null),
    (null,1620038848,'AutoSync','eliminazione','AutoSync ha prelevato la sacca dal magazzino locale per darla al magazzino centrale', 'IT-NA206000-Apos-20210518-0003', null),
    (null,1620038847,'206001','inserimento','Mario ha raccolto la sacca mettendola nel magazzino locale(206)', 'IT-NA206000-Apos-20210519-0004', null),
    (null,1620038850,'206001','LOGOUT','Mario si è loggato nel sistema ', 'IT-NA206000-Apos-20210519-0004', null),
    (null,1620038852,'ManualSync','eliminazione','Franco ha prelevato la sacca dal magazzino locale per darla al magazzino centrale', 'IT-NA206000-Apos-20210519-0004', 'IP: 95.249.112.71 user_agent: Mozilla Firefox');


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
 -- Es. 1 | Administrator | è l'utente amministratore | true | true | true | true | false | false | false | false | false
);

insert into Roles(idrole, rolename, roledescr, 
					getLog, createUser, editUser, 
					dropUser, setMasterNode, listBag, 
					addBag, removeBag, checkBag ) values
	(1,'Administrator', 'è l\'utente amministratore', true, true, true, true, false, false, false,false,false),
    (2,'Test1', 'ruolo di test 1', true, false, true, false, false, false, true,true,false),
    (3,'Test2', 'ruolo di test 2', true, true, false, true, true, false, false,false,false);

drop table if exists Users;

CREATE TABLE Users (
 iduser int NOT NULL PRIMARY KEY,
 username char(20) NOT NULL,
 password char(32) NOT NULL, -- it contains MD5() password
 lastaccess int NOT NULL,
 idrole int NOT NULL,
 
 foreign key (idrole) references Roles(idrole)
 -- Es. 1 | GCanfora | ea66ebb84ff0940dd72a35d12bcd8a72 | 1620038942 | 1
);

insert into Users(iduser, username, password, lastaccess, idrole) values
	(1, 'GCanfora', 'ea66ebb84ff0940dd72a35d12bcd8a72', 1620038942, 1),
    (2, 'Peppe', 'ea66ebb84ff0940dd72a35d12bcd8a72', 1620038943, 2),
    (3, 'Antonello', 'ea66ebb84ff0940dd72a35d12bcd8a72', 1620038944, 3),
    (4, 'Luigino', 'ea66ebb84ff0940dd72a35d12bcd8a72', 1620038945, 3);



