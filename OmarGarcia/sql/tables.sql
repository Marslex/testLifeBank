/*
NOTA: EJECUTAR CREAR USUARIO Y BASE DE DATOS CONECTADO A UN SUPER USUARIO
LUEGO DESCONECTAR Y CONECTAR CON LA BASE DE DATOS CREADA
PARA TERMINAR DE EJECUTAR SCRIPT
*/

/*
CREAR USUARIO DE BASE DE DATOS
*/

CREATE USER usr_app_lifebank WITH ENCRYPTED PASSWORD 'thisIsAHardPassword';

/*
CREAR BASE DE DATOS
*/

CREATE DATABASE lifebank
  WITH OWNER = usr_app_lifebank
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'Spanish_Spain.1252'
       LC_CTYPE = 'Spanish_Spain.1252'
       CONNECTION LIMIT = -1;
GRANT ALL ON DATABASE lifebank TO usr_app_lifebank;

/*
CREAR ESQUEMAS DE BASE DE DATOS
*/

CREATE SCHEMA acc_lifebank
  AUTHORIZATION usr_app_lifebank;

CREATE SCHEMA aut_lifebank
  AUTHORIZATION usr_app_lifebank;

CREATE SCHEMA cus_lifebank
  AUTHORIZATION usr_app_lifebank;

CREATE SCHEMA txn_lifebank
  AUTHORIZATION usr_app_lifebank;

GRANT ALL ON SCHEMA acc_lifebank TO usr_app_lifebank;
GRANT ALL ON SCHEMA aut_lifebank TO usr_app_lifebank;
GRANT ALL ON SCHEMA cus_lifebank TO usr_app_lifebank;
GRANT ALL ON SCHEMA txn_lifebank TO usr_app_lifebank;

/*
CREAR SEQUENCIAS
*/

CREATE SEQUENCE acc_lifebank.account_id_account_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 500;
ALTER TABLE acc_lifebank.account_id_account_seq
  OWNER TO usr_app_lifebank;

CREATE SEQUENCE aut_lifebank.user_id_user_seq
  INCREMENT 1
  MINVALUE 0
  MAXVALUE 9999999
  START 0
  CACHE 50;
ALTER TABLE aut_lifebank.user_id_user_seq
  OWNER TO usr_app_lifebank;

/*
CREAR TABLAS
*/

CREATE TABLE cus_lifebank.customer
(
  dui character varying(9) NOT NULL,
  email character varying(100),
  last_anme character varying(100),
  name character varying(100),
  telefono character varying(30),
  CONSTRAINT customer_pkey PRIMARY KEY (dui)
);
ALTER TABLE cus_lifebank.customer
  OWNER TO usr_app_lifebank;

CREATE TABLE acc_lifebank.account
(
  id_account character varying(255) NOT NULL,
  end_date date,
  name character varying(50),
  start_date date,
  state character varying(1),
  id_customer character varying(9),
  CONSTRAINT account_pkey PRIMARY KEY (id_account),
  CONSTRAINT customer_fkey FOREIGN KEY (id_customer)
      REFERENCES cus_lifebank.customer (dui) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

ALTER TABLE acc_lifebank.account
  OWNER TO usr_app_lifebank;

CREATE TABLE acc_lifebank.credit_account
(
  id_account character varying(255) NOT NULL,
  available numeric(9,2),
  interest_amount numeric(9,4),
  interest_rate numeric(9,4),
  acc_limit numeric(9,2),
  monthly_cut integer,
  
  CONSTRAINT credit_account_pkey PRIMARY KEY (id_account),
  CONSTRAINT account_fkey FOREIGN KEY (id_account)
      REFERENCES acc_lifebank.account (id_account) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
ALTER TABLE acc_lifebank.credit_account
  OWNER TO usr_app_lifebank;

CREATE TABLE acc_lifebank.loan_account
(
  debt numeric(9,2),
  interest_amount numeric(9,2),
  interest_rate numeric(9,2),
  total numeric(9,2),
  id_account character varying(255) NOT NULL,
  CONSTRAINT loan_account_pkey PRIMARY KEY (id_account),
  CONSTRAINT account_fkey FOREIGN KEY (id_account)
      REFERENCES acc_lifebank.account (id_account) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
ALTER TABLE acc_lifebank.loan_account
  OWNER TO usr_app_lifebank;

CREATE TABLE acc_lifebank.personal_account
(
  available numeric(9,2),
  reserved numeric(9,2),
  id_account character varying(255) NOT NULL,
  CONSTRAINT personal_account_pkey PRIMARY KEY (id_account),
  CONSTRAINT account_fkey FOREIGN KEY (id_account)
      REFERENCES acc_lifebank.account (id_account) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
ALTER TABLE acc_lifebank.personal_account
  OWNER TO usr_app_lifebank;

CREATE TABLE aut_lifebank."user"
(
  id_user integer NOT NULL,
  password character varying(500),
  state boolean,
  user_name character varying(100),
  id_customer character varying(9),
  CONSTRAINT user_pkey PRIMARY KEY (id_user),
  CONSTRAINT customer_fkey FOREIGN KEY (id_customer)
      REFERENCES cus_lifebank.customer (dui) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
ALTER TABLE aut_lifebank."user"
  OWNER TO usr_app_lifebank;


CREATE TABLE txn_lifebank.transaction
(
  transaction_number character varying(18) NOT NULL,
  amount double precision,
  description character varying(200),
  transaction_date timestamp without time zone,
  transaction_type character varying(1),
  id_account character varying(255),
  CONSTRAINT transaction_pkey PRIMARY KEY (transaction_number),
  CONSTRAINT account_fkey FOREIGN KEY (id_account)
      REFERENCES acc_lifebank.account (id_account) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
ALTER TABLE txn_lifebank.transaction
  OWNER TO usr_app_lifebank;

