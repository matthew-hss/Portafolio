--------------------------------------------------------
-- DATA FOR ROLE
--------------------------------------------------------

	INSERT INTO ROLE (NAME) VALUES ('ADMIN');
	INSERT INTO ROLE (NAME) VALUES ('STAFF');
	INSERT INTO ROLE (NAME) VALUES ('PUBLIC');


--------------------------------------------------------
--  USER
--------------------------------------------------------

	INSERT INTO USERS (PASSWORD, ROLE_ID, NAME, RUT, ACTIVE) VALUES ('ffd6838235902d214ccd3297a0bed18c62d0e15356246fd78410cbd53d43c35e11b5f0e515048426b981972deaa47bf5f72714255cef7d3ea8a8a775c6250a42', 1, 'Usuario Administrador', 12111111, 0);
	INSERT INTO USERS (PASSWORD, ROLE_ID, NAME, RUT, ACTIVE) VALUES ('e9bdfe21f395dfaec00aa996bd1009daa7d1c9038963308aa526000c6217ab989ddfb5142faf1738ddd0bbc18fea4087b2a5ea31078fc846a3a9a8dcbab17bc6', 5, 'Javier González M.', 15886654, 0);