CREATE SEQUENCE acc_lifebank.user_id_user_seq
  INCREMENT 1
  MINVALUE 0
  MAXVALUE 9999999
  START 0
  CACHE 50;
ALTER TABLE acc_lifebank.user_id_user_seq
  OWNER TO usr_app_lifebank;

CREATE TABLE "acc_lifebank"."user"
(
  id_user integer NOT NULL DEFAULT nextval('acc_lifebank.user_id_user_seq'::regclass),
  user_name character varying(100) NOT NULL,
  password character varying(250) NOT NULL,
  state character varying(1) not null default 'A',
  CONSTRAINT user_primary_key_id_user PRIMARY KEY (id_user)
)
;

