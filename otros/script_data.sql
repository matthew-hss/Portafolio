--------------------------------------------------------
-- ROLE
--------------------------------------------------------

	INSERT INTO "PORTAFOLIO"."ROLE" (NAME) VALUES ('ADMIN');
	INSERT INTO "PORTAFOLIO"."ROLE" (NAME) VALUES ('STAFF');
	INSERT INTO "PORTAFOLIO"."ROLE" (NAME) VALUES ('GENERAL');


--------------------------------------------------------
--  USER
--------------------------------------------------------

	INSERT INTO "PORTAFOLIO"."USERS" (PASSWORD, ROLE_ID, NAME, RUT, ACTIVE) VALUES ('ffd6838235902d214ccd3297a0bed18c62d0e15356246fd78410cbd53d43c35e11b5f0e515048426b981972deaa47bf5f72714255cef7d3ea8a8a775c6250a42', 1, 'Usuario Administrador', 12111111, 1);
	INSERT INTO "PORTAFOLIO"."USERS" (PASSWORD, ROLE_ID, NAME, RUT, ACTIVE) VALUES ('e9bdfe21f395dfaec00aa996bd1009daa7d1c9038963308aa526000c6217ab989ddfb5142faf1738ddd0bbc18fea4087b2a5ea31078fc846a3a9a8dcbab17bc6', 3, 'Javier González M.', 15886654, 1);


--------------------------------------------------------
--  JOBTITLE
--------------------------------------------------------

	INSERT INTO "PORTAFOLIO"."JOBTITLE" (NAME) VALUES ('Gerente');
	INSERT INTO "PORTAFOLIO"."JOBTITLE" (NAME) VALUES ('Jefe');
	INSERT INTO "PORTAFOLIO"."JOBTITLE" (NAME) VALUES ('Secretaria');
	INSERT INTO "PORTAFOLIO"."JOBTITLE" (NAME) VALUES ('Obrero');


--------------------------------------------------------
--  FUNCTIONARIES
--------------------------------------------------------

	INSERT INTO "PORTAFOLIO"."FUNCTIONARY" (PASSWORD, JOBTITLE_ID, NAME, SURNAME, RUT) VALUES ('e9bdfe21f395dfaec00aa996bd1009daa7d1c9038963308aa526000c6217ab989ddfb5142faf1738ddd0bbc18fea4087b2a5ea31078fc846a3a9a8dcbab17bc6', 1, 'Javier', 'González M.', 15886654);

--------------------------------------------------------
-- PRODUCT
--------------------------------------------------------

  INSERT INTO "PORTAFOLIO"."PRODUCT" (NAME, PRICE) VALUES ('Desayuno', 2000);
  INSERT INTO "PORTAFOLIO"."PRODUCT" (NAME, PRICE) VALUES ('Almuerzo', 3200);
  INSERT INTO "PORTAFOLIO"."PRODUCT" (NAME, PRICE) VALUES ('Once', 2200);
  INSERT INTO "PORTAFOLIO"."PRODUCT" (NAME, PRICE) VALUES ('Cena Uno', 3500);
  INSERT INTO "PORTAFOLIO"."PRODUCT" (NAME, PRICE) VALUES ('Cena Dos', 3500);
  
  --------------------------------------------------------
-- MEALSERVICE
--------------------------------------------------------

  INSERT INTO MEALSERVICE (STARTTIME, ENDTIME, PRODUCT_ID) VALUES(TO_DATE('8:00', 'hh24:mi'),TO_DATE('12:00', 'hh24:mi'),1);
  INSERT INTO MEALSERVICE (STARTTIME, ENDTIME, PRODUCT_ID) VALUES(TO_DATE('12:00', 'hh24:mi'),TO_DATE('14:00', 'hh24:mi'),2);
  INSERT INTO MEALSERVICE (STARTTIME, ENDTIME, PRODUCT_ID) VALUES(TO_DATE('18:00', 'hh24:mi'),TO_DATE('21:00', 'hh24:mi'),3);
  INSERT INTO MEALSERVICE (STARTTIME, ENDTIME, PRODUCT_ID) VALUES(TO_DATE('21:00', 'hh24:mi'),TO_DATE('23:00', 'hh24:mi'),4);
  INSERT INTO MEALSERVICE (STARTTIME, ENDTIME, PRODUCT_ID) VALUES(TO_DATE('23:00', 'hh24:mi'),TO_DATE('7:00', 'hh24:mi'),5);