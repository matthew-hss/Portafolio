--------------------------------------------------------
-- CREATE USER PORTAFOLIO
--------------------------------------------------------

  CREATE USER portafolio IDENTIFIED BY "Portafolio.2017";
  GRANT ALL PRIVILEGES TO portafolio;

--------------------------------------------------------
-- SEQUENCES FOR AUTOINCREMENT ID
--------------------------------------------------------

   CREATE SEQUENCE  "PORTAFOLIO"."JOBTITLE_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
   CREATE SEQUENCE  "PORTAFOLIO"."FUNCTIONARY_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
   CREATE SEQUENCE  "PORTAFOLIO"."PLACE_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
   CREATE SEQUENCE  "PORTAFOLIO"."PRODUCT_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
   CREATE SEQUENCE  "PORTAFOLIO"."SALEITEM_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
   CREATE SEQUENCE  "PORTAFOLIO"."SALE_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
   CREATE SEQUENCE  "PORTAFOLIO"."MEALSERVICE_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
   CREATE SEQUENCE  "PORTAFOLIO"."USERS_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
   CREATE SEQUENCE  "PORTAFOLIO"."VOUCHERAMOUNT_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
   CREATE SEQUENCE  "PORTAFOLIO"."VOUCHER_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
   CREATE SEQUENCE  "PORTAFOLIO"."WORKSHIFT_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
   CREATE SEQUENCE  "PORTAFOLIO"."WSASSIGNMENT_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;


--------------------------------------------------------
--  TABLES
--------------------------------------------------------

  CREATE TABLE "PORTAFOLIO"."JOBTITLE" 
   (	"ID" NUMBER(*,0), 
	"NAME" VARCHAR2(64 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

  CREATE TABLE "PORTAFOLIO"."FUNCTIONARY" 
   (	"ID" NUMBER(*,0), 
	"PASSWORD" VARCHAR2(128 BYTE), 
	"RUT" NUMBER(*,0), 
	"NAME" VARCHAR2(64 BYTE), 
	"SURNAME" VARCHAR2(64 BYTE), 
	"JOBTITLE_ID" NUMBER(*,0),
  "EMAIL" VARCHAR2(128 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

  CREATE TABLE "PORTAFOLIO"."PLACE" 
   (	"ID" NUMBER(*,0), 
	"NAME" VARCHAR2(128 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

  CREATE TABLE "PORTAFOLIO"."PRODUCT" 
   (	"ID" NUMBER(*,0), 
	"NAME" VARCHAR2(64 BYTE), 
	"PRICE" NUMBER(*,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

  CREATE TABLE "PORTAFOLIO"."SALE" 
   (	"ID" NUMBER(*,0), 
	"TOTAL" NUMBER(*,0), 
	"PLACE_ID" NUMBER(*,0), 
	"VOUCHER_ID" NUMBER(*,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

  CREATE TABLE "PORTAFOLIO"."SALEITEM" 
   (	"ID" NUMBER(*,0), 
	"QUANTITY" NUMBER(*,0), 
	"SALE_ID" NUMBER(*,0), 
	"PRODUCT_ID" NUMBER(*,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

  CREATE TABLE "PORTAFOLIO"."MEALSERVICE" 
   (	"ID" NUMBER(*,0), 
	"STARTTIME" TIMESTAMP (6), 
	"ENDTIME" TIMESTAMP (6), 
	"PRODUCT_ID" NUMBER(*,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

  CREATE TABLE "PORTAFOLIO"."USERS" 
   (	"ID" NUMBER(*,0), 
	"PASSWORD" VARCHAR2(128 BYTE), 
	"NAME" VARCHAR2(64 BYTE), 
	"RUT" NUMBER(*,0),
	"ROLE_ID" NUMBER(*,0),
	"ACTIVE" NUMBER(1,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

  CREATE TABLE "PORTAFOLIO"."VOUCHER" 
   (	"ID" NUMBER(*,0), 
	"CODE" VARCHAR2(16 BYTE), 
	"USED" NUMBER(1,0), 
	"DATETIME" DATE, 
	"VOUCHERAMOUNT_ID" NUMBER(*,0), 
	"FUNCTIONARY_ID" NUMBER(*,0), 
	"SALE_ID" NUMBER(*,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

  CREATE TABLE "PORTAFOLIO"."VOUCHERAMOUNT" 
   (	"ID" NUMBER(*,0), 
	"AMOUNT" NUMBER(*,0), 
	"MEALSERVICE_ID" NUMBER(*,0), 
	"JOBTITLE_ID" NUMBER(*,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

  CREATE TABLE "PORTAFOLIO"."WORKSHIFT" 
   (	"ID" NUMBER(*,0), 
	"STARTTIME" TIMESTAMP (6), 
	"ENDTIME" TIMESTAMP (6)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

  CREATE TABLE "PORTAFOLIO"."WSASSIGNMENT" 
   (	"ID" NUMBER(*,0), 
	"STARTDATE" DATE, 
	"ENDDATE" DATE, 
	"WORKSHIFT_ID" NUMBER(*,0), 
	"FUNCTIONARY_ID" NUMBER(*,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
  
  
--------------------------------------------------------
--  PRIMARY KEYS
--------------------------------------------------------

  CREATE UNIQUE INDEX "PORTAFOLIO"."USERS_PK" ON "PORTAFOLIO"."USERS" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

  CREATE UNIQUE INDEX "PORTAFOLIO"."WORKSHIFT_PK" ON "PORTAFOLIO"."WORKSHIFT" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

  CREATE UNIQUE INDEX "PORTAFOLIO"."SALE_PK" ON "PORTAFOLIO"."SALE" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

  CREATE UNIQUE INDEX "PORTAFOLIO"."MEALSERVICE_PK" ON "PORTAFOLIO"."MEALSERVICE" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

  CREATE UNIQUE INDEX "PORTAFOLIO"."JOBTITLE_PK" ON "PORTAFOLIO"."JOBTITLE" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

  CREATE UNIQUE INDEX "PORTAFOLIO"."PLACE_PK" ON "PORTAFOLIO"."PLACE" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

  CREATE UNIQUE INDEX "PORTAFOLIO"."SALEITEM_PK" ON "PORTAFOLIO"."SALEITEM" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

  CREATE UNIQUE INDEX "PORTAFOLIO"."FUNCTIONARY_PK" ON "PORTAFOLIO"."FUNCTIONARY" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

  CREATE UNIQUE INDEX "PORTAFOLIO"."VOUCHERAMOUNT_PK" ON "PORTAFOLIO"."VOUCHERAMOUNT" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

  CREATE UNIQUE INDEX "PORTAFOLIO"."PRODUCT_PK" ON "PORTAFOLIO"."PRODUCT" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

  CREATE UNIQUE INDEX "PORTAFOLIO"."WSASSIGNMENT_PK" ON "PORTAFOLIO"."WSASSIGNMENT" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

  CREATE UNIQUE INDEX "PORTAFOLIO"."VOUCHER_PK" ON "PORTAFOLIO"."VOUCHER" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
  
  
  
--------------------------------------------------------
--  CONSTRAINTS
--------------------------------------------------------

  ALTER TABLE "PORTAFOLIO"."PRODUCT" ADD CONSTRAINT "PRODUCT_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "PORTAFOLIO"."PRODUCT" MODIFY ("PRICE" NOT NULL ENABLE);
  ALTER TABLE "PORTAFOLIO"."PRODUCT" MODIFY ("NAME" NOT NULL ENABLE);
  ALTER TABLE "PORTAFOLIO"."PRODUCT" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "PORTAFOLIO"."PRODUCT" ADD CONSTRAINT PRODUCT_UNQ UNIQUE("NAME");

  ALTER TABLE "PORTAFOLIO"."SALE" ADD CONSTRAINT "SALE_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "PORTAFOLIO"."SALE" MODIFY ("PLACE_ID" NOT NULL ENABLE);
  ALTER TABLE "PORTAFOLIO"."SALE" MODIFY ("TOTAL" NOT NULL ENABLE);
  ALTER TABLE "PORTAFOLIO"."SALE" MODIFY ("ID" NOT NULL ENABLE);

  ALTER TABLE "PORTAFOLIO"."PLACE" ADD CONSTRAINT "PLACE_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "PORTAFOLIO"."PLACE" MODIFY ("NAME" NOT NULL ENABLE);
  ALTER TABLE "PORTAFOLIO"."PLACE" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "PORTAFOLIO"."PLACE" ADD CONSTRAINT PLACE_UNQ UNIQUE("NAME");

  ALTER TABLE "PORTAFOLIO"."JOBTITLE" ADD CONSTRAINT "JOBTITLE_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "PORTAFOLIO"."JOBTITLE" MODIFY ("NAME" NOT NULL ENABLE);
  ALTER TABLE "PORTAFOLIO"."JOBTITLE" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "PORTAFOLIO"."JOBTITLE" ADD CONSTRAINT JOBTITLE_UNQ UNIQUE("NAME");

  ALTER TABLE "PORTAFOLIO"."FUNCTIONARY" ADD CONSTRAINT "FUNCTIONARY_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "PORTAFOLIO"."FUNCTIONARY" MODIFY ("JOBTITLE_ID" NOT NULL ENABLE);
  ALTER TABLE "PORTAFOLIO"."FUNCTIONARY" MODIFY ("SURNAME" NOT NULL ENABLE);
  ALTER TABLE "PORTAFOLIO"."FUNCTIONARY" MODIFY ("NAME" NOT NULL ENABLE);
  ALTER TABLE "PORTAFOLIO"."FUNCTIONARY" MODIFY ("RUT" NOT NULL ENABLE);
  ALTER TABLE "PORTAFOLIO"."FUNCTIONARY" MODIFY ("PASSWORD" NOT NULL ENABLE);
  ALTER TABLE "PORTAFOLIO"."FUNCTIONARY" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "PORTAFOLIO"."FUNCTIONARY" MODIFY ("EMAIL" NOT NULL ENABLE);
  ALTER TABLE "PORTAFOLIO"."FUNCTIONARY" ADD CONSTRAINT FUNCTIONARY_UNQ1 UNIQUE("RUT");
  ALTER TABLE "PORTAFOLIO"."FUNCTIONARY" ADD CONSTRAINT FUNCTIONARY_UNQ2 UNIQUE("EMAIL");

  ALTER TABLE "PORTAFOLIO"."VOUCHERAMOUNT" ADD CONSTRAINT "VOUCHERAMOUNT_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "PORTAFOLIO"."VOUCHERAMOUNT" MODIFY ("JOBTITLE_ID" NOT NULL ENABLE);
  ALTER TABLE "PORTAFOLIO"."VOUCHERAMOUNT" MODIFY ("MEALSERVICE_ID" NOT NULL ENABLE);
  ALTER TABLE "PORTAFOLIO"."VOUCHERAMOUNT" MODIFY ("AMOUNT" NOT NULL ENABLE);
  ALTER TABLE "PORTAFOLIO"."VOUCHERAMOUNT" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "PORTAFOLIO"."VOUCHERAMOUNT" ADD CONSTRAINT VOUCHERAMOUNT_UNQ UNIQUE("JOBTITLE_ID", "MEALSERVICE_ID");

  ALTER TABLE "PORTAFOLIO"."USERS" ADD CONSTRAINT "USERS_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "PORTAFOLIO"."USERS" MODIFY ("ROLE_ID" NOT NULL ENABLE);
  ALTER TABLE "PORTAFOLIO"."USERS" MODIFY ("PASSWORD" NOT NULL ENABLE);
  ALTER TABLE "PORTAFOLIO"."USERS" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "PORTAFOLIO"."USERS" ADD CHECK ("ACTIVE" IN(0,1)) ENABLE;
  ALTER TABLE "PORTAFOLIO"."USERS" MODIFY ("ACTIVE" NOT NULL ENABLE);
  ALTER TABLE "PORTAFOLIO"."USERS" MODIFY ("NAME" NOT NULL ENABLE);
  ALTER TABLE "PORTAFOLIO"."USERS" MODIFY ("RUT" NOT NULL ENABLE);
  ALTER TABLE "PORTAFOLIO"."USERS" ADD CONSTRAINT USERS_UNQ UNIQUE("RUT");

  ALTER TABLE "PORTAFOLIO"."WSASSIGNMENT" ADD CONSTRAINT "WSASSIGNMENT_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "PORTAFOLIO"."WSASSIGNMENT" MODIFY ("FUNCTIONARY_ID" NOT NULL ENABLE);
  ALTER TABLE "PORTAFOLIO"."WSASSIGNMENT" MODIFY ("WORKSHIFT_ID" NOT NULL ENABLE);
  ALTER TABLE "PORTAFOLIO"."WSASSIGNMENT" MODIFY ("ENDDATE" NOT NULL ENABLE);
  ALTER TABLE "PORTAFOLIO"."WSASSIGNMENT" MODIFY ("STARTDATE" NOT NULL ENABLE);
  ALTER TABLE "PORTAFOLIO"."WSASSIGNMENT" MODIFY ("ID" NOT NULL ENABLE);

  ALTER TABLE "PORTAFOLIO"."SALEITEM" ADD CONSTRAINT "SALEITEM_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "PORTAFOLIO"."SALEITEM" MODIFY ("PRODUCT_ID" NOT NULL ENABLE);
  ALTER TABLE "PORTAFOLIO"."SALEITEM" MODIFY ("SALE_ID" NOT NULL ENABLE);
  ALTER TABLE "PORTAFOLIO"."SALEITEM" MODIFY ("QUANTITY" NOT NULL ENABLE);
  ALTER TABLE "PORTAFOLIO"."SALEITEM" MODIFY ("ID" NOT NULL ENABLE);

  ALTER TABLE "PORTAFOLIO"."VOUCHER" ADD CHECK ("USED" IN(0,1)) ENABLE;
  ALTER TABLE "PORTAFOLIO"."VOUCHER" MODIFY ("FUNCTIONARY_ID" NOT NULL ENABLE);
  ALTER TABLE "PORTAFOLIO"."VOUCHER" MODIFY ("VOUCHERAMOUNT_ID" NOT NULL ENABLE);
  ALTER TABLE "PORTAFOLIO"."VOUCHER" MODIFY ("MEALSERVICE_ID" NOT NULL ENABLE);
  ALTER TABLE "PORTAFOLIO"."VOUCHER" ADD CONSTRAINT "VOUCHER_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "PORTAFOLIO"."VOUCHER" MODIFY ("DATETIME" NOT NULL ENABLE);
  ALTER TABLE "PORTAFOLIO"."VOUCHER" MODIFY ("USED" NOT NULL ENABLE);
  ALTER TABLE "PORTAFOLIO"."VOUCHER" MODIFY ("CODE" NOT NULL ENABLE);
  ALTER TABLE "PORTAFOLIO"."VOUCHER" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "PORTAFOLIO"."VOUCHER" ADD CONSTRAINT VOUCHER_UNQ UNIQUE("CODE");

  ALTER TABLE "PORTAFOLIO"."MEALSERVICE" ADD CONSTRAINT "MEALSERVICE_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "PORTAFOLIO"."MEALSERVICE" MODIFY ("PRODUCT_ID" NOT NULL ENABLE);
  ALTER TABLE "PORTAFOLIO"."MEALSERVICE" MODIFY ("ENDTIME" NOT NULL ENABLE);
  ALTER TABLE "PORTAFOLIO"."MEALSERVICE" MODIFY ("STARTTIME" NOT NULL ENABLE);
  ALTER TABLE "PORTAFOLIO"."MEALSERVICE" MODIFY ("ID" NOT NULL ENABLE);

  ALTER TABLE "PORTAFOLIO"."WORKSHIFT" ADD CONSTRAINT "WORKSHIFT_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "PORTAFOLIO"."WORKSHIFT" MODIFY ("ENDTIME" NOT NULL ENABLE);
  ALTER TABLE "PORTAFOLIO"."WORKSHIFT" MODIFY ("STARTTIME" NOT NULL ENABLE);
  ALTER TABLE "PORTAFOLIO"."WORKSHIFT" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "PORTAFOLIO"."WORKSHIFT" ADD CONSTRAINT WORKSHIFT_UNQ UNIQUE("STARTTIME", "ENDTIME");



--------------------------------------------------------
-- FOREIGN KEYS
--------------------------------------------------------

  ALTER TABLE "PORTAFOLIO"."FUNCTIONARY" ADD CONSTRAINT "FUNCTIONARY_FK1" FOREIGN KEY ("JOBTITLE_ID")
	  REFERENCES "PORTAFOLIO"."JOBTITLE" ("ID") ON DELETE CASCADE ENABLE;

  ALTER TABLE "PORTAFOLIO"."SALE" ADD CONSTRAINT "SALE_FK1" FOREIGN KEY ("PLACE_ID")
	  REFERENCES "PORTAFOLIO"."PLACE" ("ID") ON DELETE CASCADE ENABLE;
  ALTER TABLE "PORTAFOLIO"."SALE" ADD CONSTRAINT "SALE_FK2" FOREIGN KEY ("VOUCHER_ID")
	  REFERENCES "PORTAFOLIO"."VOUCHER" ("ID") ON DELETE CASCADE ENABLE;

  ALTER TABLE "PORTAFOLIO"."SALEITEM" ADD CONSTRAINT "SALEITEM_FK1" FOREIGN KEY ("PRODUCT_ID")
	  REFERENCES "PORTAFOLIO"."PRODUCT" ("ID") ON DELETE CASCADE ENABLE;
  ALTER TABLE "PORTAFOLIO"."SALEITEM" ADD CONSTRAINT "SALEITEM_FK2" FOREIGN KEY ("SALE_ID")
	  REFERENCES "PORTAFOLIO"."SALE" ("ID") ON DELETE CASCADE ENABLE;

  ALTER TABLE "PORTAFOLIO"."MEALSERVICE" ADD CONSTRAINT "MEALSERVICE_FK1" FOREIGN KEY ("PRODUCT_ID")
	  REFERENCES "PORTAFOLIO"."PRODUCT" ("ID") ON DELETE CASCADE ENABLE;

  ALTER TABLE "PORTAFOLIO"."VOUCHER" ADD CONSTRAINT "VOUCHER_FK1" FOREIGN KEY ("VOUCHERAMOUNT_ID")
	  REFERENCES "PORTAFOLIO"."VOUCHERAMOUNT" ("ID") ON DELETE CASCADE ENABLE;
  ALTER TABLE "PORTAFOLIO"."VOUCHER" ADD CONSTRAINT "VOUCHER_FK2" FOREIGN KEY ("FUNCTIONARY_ID")
	  REFERENCES "PORTAFOLIO"."FUNCTIONARY" ("ID") ON DELETE CASCADE ENABLE;
  ALTER TABLE "PORTAFOLIO"."VOUCHER" ADD CONSTRAINT "VOUCHER_FK3" FOREIGN KEY ("SALE_ID")
	  REFERENCES "PORTAFOLIO"."SALE" ("ID") ON DELETE CASCADE ENABLE;
  ALTER TABLE "PORTAFOLIO"."VOUCHER" ADD CONSTRAINT "VOUCHER_FK4" FOREIGN KEY ("MEALSERVICE_ID")
	  REFERENCES "PORTAFOLIO"."MEALSERVICE" ("ID") ON DELETE CASCADE ENABLE;

  ALTER TABLE "PORTAFOLIO"."VOUCHERAMOUNT" ADD CONSTRAINT "VOUCHERAMOUNT_FK1" FOREIGN KEY ("JOBTITLE_ID")
	  REFERENCES "PORTAFOLIO"."JOBTITLE" ("ID") ON DELETE CASCADE ENABLE;
  ALTER TABLE "PORTAFOLIO"."VOUCHERAMOUNT" ADD CONSTRAINT "VOUCHERAMOUNT_FK2" FOREIGN KEY ("MEALSERVICE_ID")
	  REFERENCES "PORTAFOLIO"."MEALSERVICE" ("ID") ON DELETE CASCADE ENABLE;

  ALTER TABLE "PORTAFOLIO"."WSASSIGNMENT" ADD CONSTRAINT "WSASSIGNMENT_FK1" FOREIGN KEY ("WORKSHIFT_ID")
	  REFERENCES "PORTAFOLIO"."WORKSHIFT" ("ID") ON DELETE CASCADE ENABLE;
  ALTER TABLE "PORTAFOLIO"."WSASSIGNMENT" ADD CONSTRAINT "WSASSIGNMENT_FK2" FOREIGN KEY ("FUNCTIONARY_ID")
	  REFERENCES "PORTAFOLIO"."FUNCTIONARY" ("ID") ON DELETE CASCADE ENABLE;



--------------------------------------------------------
--  TRIGGERS
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "PORTAFOLIO"."FUNCTIONARY_TRG" 
BEFORE INSERT ON "PORTAFOLIO"."FUNCTIONARY"
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT "PORTAFOLIO"."FUNCTIONARY_SEQ".NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "PORTAFOLIO"."FUNCTIONARY_TRG" ENABLE;


  CREATE OR REPLACE TRIGGER "PORTAFOLIO"."JOBTITLE_TRG" 
BEFORE INSERT ON "PORTAFOLIO"."JOBTITLE" 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT "PORTAFOLIO"."JOBTITLE_SEQ".NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "PORTAFOLIO"."JOBTITLE_TRG" ENABLE;


  CREATE OR REPLACE TRIGGER "PORTAFOLIO"."PLACE_TRG" 
BEFORE INSERT ON "PORTAFOLIO"."PLACE" 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT "PORTAFOLIO"."PLACE_SEQ".NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "PORTAFOLIO"."PLACE_TRG" ENABLE;


  CREATE OR REPLACE TRIGGER "PORTAFOLIO"."PRODUCT_TRG" 
BEFORE INSERT ON "PORTAFOLIO"."PRODUCT" 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT "PORTAFOLIO"."PRODUCT_SEQ".NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "PORTAFOLIO"."PRODUCT_TRG" ENABLE;


  CREATE OR REPLACE TRIGGER "PORTAFOLIO"."SALEITEM_TRG" 
BEFORE INSERT ON "PORTAFOLIO"."SALEITEM" 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT "PORTAFOLIO"."SALEITEM_SEQ".NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "PORTAFOLIO"."SALEITEM_TRG" ENABLE;


  CREATE OR REPLACE TRIGGER "PORTAFOLIO"."SALE_TRG" 
BEFORE INSERT ON "PORTAFOLIO"."SALE" 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT "PORTAFOLIO"."SALE_SEQ".NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "PORTAFOLIO"."SALE_TRG" ENABLE;


  CREATE OR REPLACE TRIGGER "PORTAFOLIO"."MEALSERVICE_TRG" 
BEFORE INSERT ON "PORTAFOLIO"."MEALSERVICE" 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT "PORTAFOLIO"."MEALSERVICE_SEQ".NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "PORTAFOLIO"."MEALSERVICE_TRG" ENABLE;


  CREATE OR REPLACE TRIGGER "PORTAFOLIO"."USERS_TRG" 
BEFORE INSERT ON "PORTAFOLIO"."USERS" 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT "PORTAFOLIO"."USERS_SEQ".NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "PORTAFOLIO"."USERS_TRG" ENABLE;


  CREATE OR REPLACE TRIGGER "PORTAFOLIO"."VOUCHERAMOUNT_TRG" 
BEFORE INSERT ON "PORTAFOLIO"."VOUCHERAMOUNT" 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT "PORTAFOLIO"."VOUCHERAMOUNT_SEQ".NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "PORTAFOLIO"."VOUCHERAMOUNT_TRG" ENABLE;


  CREATE OR REPLACE TRIGGER "PORTAFOLIO"."VOUCHER_TRG" 
BEFORE INSERT ON "PORTAFOLIO"."VOUCHER" 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT "PORTAFOLIO"."VOUCHER_SEQ".NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "PORTAFOLIO"."VOUCHER_TRG" ENABLE;


  CREATE OR REPLACE TRIGGER "PORTAFOLIO"."WORKSHIFT_TRG" 
BEFORE INSERT ON "PORTAFOLIO"."WORKSHIFT" 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT "PORTAFOLIO"."WORKSHIFT_SEQ".NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "PORTAFOLIO"."WORKSHIFT_TRG" ENABLE;


  CREATE OR REPLACE TRIGGER "PORTAFOLIO"."WSASSIGNMENT_TRG" 
BEFORE INSERT ON "PORTAFOLIO"."WSASSIGNMENT" 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT "PORTAFOLIO"."WSASSIGNMENT_SEQ".NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "PORTAFOLIO"."WSASSIGNMENT_TRG" ENABLE;