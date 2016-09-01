-- Generated by Oracle SQL Developer Data Modeler 4.1.3.901
--   at:        2016-08-31 18:24:11 GMT-08:00
--   site:      Oracle Database 11g
--   type:      Oracle Database 11g




DROP TABLE ANTECEDENTE CASCADE CONSTRAINTS ;

DROP TABLE GINECOLOGICO CASCADE CONSTRAINTS ;

DROP TABLE HISTORIACLINICA CASCADE CONSTRAINTS ;

DROP TABLE LINEASEMERGENCIA CASCADE CONSTRAINTS ;

DROP TABLE NIVELURGENCIA CASCADE CONSTRAINTS ;

DROP TABLE SINTOMA CASCADE CONSTRAINTS ;

DROP TABLE SINTOMASXHISTORIA CASCADE CONSTRAINTS ;

DROP TABLE TRIAGE CASCADE CONSTRAINTS ;

DROP TABLE USUARIO CASCADE CONSTRAINTS ;

DROP TABLE USUARIOXANTECEDENTE CASCADE CONSTRAINTS ;

CREATE TABLE ANTECEDENTE
  (
    IDANTECEDENTE NUMBER (*,0) NOT NULL ,
    DESCRIPCION   VARCHAR2 (100 BYTE) ,
    TIPO          VARCHAR2 (50 BYTE)
  ) ;
ALTER TABLE ANTECEDENTE ADD CONSTRAINT ANTECEDENTE_PK PRIMARY KEY ( IDANTECEDENTE ) ;


CREATE TABLE GINECOLOGICO
  (
    ABORTOS              NUMBER (*,0) DEFAULT 0 NOT NULL ,
    CESAREAS             NUMBER (*,0) DEFAULT 0 NOT NULL ,
    FECHAPRIMERPERIODO   DATE ,
    FECHAULTIMOPERIODO   DATE ,
    GESTAS               NUMBER (*,0) DEFAULT 0 NOT NULL ,
    METODOANTICONCEPTIVO VARCHAR2 (45 BYTE) ,
    PARTOS               NUMBER (*,0) DEFAULT 0 NOT NULL ,
    USUARIO_IDUSUARIO    NUMBER (*,0) NOT NULL
  ) ;
ALTER TABLE GINECOLOGICO ADD CONSTRAINT GINECOLOGICO_PK PRIMARY KEY ( USUARIO_IDUSUARIO ) ;


CREATE TABLE HISTORIACLINICA
  (
    IDHISTORIACLINICA         NUMBER (*,0) NOT NULL ,
    DESCRIPCIONMOTIVOURGENCIA VARCHAR2 (500 BYTE) ,
    INICIOMOTIVOURGENCIA      DATE ,
    USUARIO_IDUSUARIO         NUMBER (*,0) NOT NULL ,
    FECHA                     DATE
  ) ;
ALTER TABLE HISTORIACLINICA ADD CONSTRAINT HISTORIACLINICA_PK PRIMARY KEY ( IDHISTORIACLINICA ) ;


CREATE TABLE LINEASEMERGENCIA
  (
    IDLINEASEMERGENCIA NUMBER (*,0) NOT NULL ,
    NUMERO             NUMBER (*,0) ,
    DESCRIPCION        VARCHAR2 (50 BYTE)
  ) ;
ALTER TABLE LINEASEMERGENCIA ADD CONSTRAINT LINEASEMERGENCIA_PK PRIMARY KEY ( IDLINEASEMERGENCIA ) ;


CREATE TABLE NIVELURGENCIA
  (
    SINTOMA_IDSINTOMA NUMBER (*,0) NOT NULL ,
    TRIAGE_IDTRIAGE   INTEGER NOT NULL ,
    PRESENCIA         CHAR (2 CHAR)
  ) ;
ALTER TABLE NIVELURGENCIA ADD CONSTRAINT NIVELURGENCIA_PK PRIMARY KEY ( SINTOMA_IDSINTOMA, TRIAGE_IDTRIAGE ) ;


CREATE TABLE SINTOMA
  (
    IDSINTOMA    NUMBER (*,0) NOT NULL ,
    DESCRIPCIONM VARCHAR2 (100 BYTE) ,
    DESCRIPCIONP VARCHAR2 (100 BYTE) ,
    COORDENADAX  INTEGER ,
    COORDENADAY  INTEGER
  ) ;
ALTER TABLE SINTOMA ADD CONSTRAINT SINTOMA_PK PRIMARY KEY ( IDSINTOMA ) ;


CREATE TABLE SINTOMASXHISTORIA
  (
    FECHA                DATE ,
    HISTORIACLINICA_IDHC NUMBER (*,0) NOT NULL ,
    SINTOMA_IDSINTOMA    NUMBER (*,0) NOT NULL ,
    PRESENCIA            VARCHAR2 (2)
  ) ;
ALTER TABLE SINTOMASXHISTORIA ADD CONSTRAINT SINTOMASXHISTORIA_PK PRIMARY KEY ( HISTORIACLINICA_IDHC, SINTOMA_IDSINTOMA ) ;


CREATE TABLE TRIAGE
  (
    IDTRIAGE    INTEGER NOT NULL ,
    NIVELTRIAGE INTEGER ,
    DX          VARCHAR2 (50 CHAR)
  ) ;
ALTER TABLE TRIAGE ADD CONSTRAINT TRIAGE_PK PRIMARY KEY ( IDTRIAGE ) ;


CREATE TABLE USUARIO
  (
    IDUSUARIO       NUMBER (*,0) NOT NULL ,
    IDFACEGOO       VARCHAR2 (50 CHAR) ,
    APELLIDO        VARCHAR2 (45 BYTE) ,
    CEDULA          NUMBER (*,0) NOT NULL ,
    CORREO          VARCHAR2 (45 BYTE) ,
    DIRECCION       VARCHAR2 (45 BYTE) ,
    FECHANACIMIENTO DATE ,
    GENERO          CHAR (1 BYTE) ,
    NOMBRE          VARCHAR2 (45 BYTE) ,
    ROL             VARCHAR2 (50 BYTE) ,
    TIPOSANGRE      VARCHAR2 (3 CHAR) ,
    EPS             VARCHAR2 (45 BYTE)
  ) ;
ALTER TABLE USUARIO ADD CONSTRAINT USUARIO_PK PRIMARY KEY ( IDUSUARIO ) ;
ALTER TABLE USUARIO ADD CONSTRAINT USUARIO_CEDULA_UN UNIQUE ( CEDULA ) ;


CREATE TABLE USUARIOXANTECEDENTE
  (
    USUARIO_IDUSUARIO         NUMBER (*,0) NOT NULL ,
    ANTECEDENTE_IDANTECEDENTE NUMBER (*,0) NOT NULL
  ) ;
ALTER TABLE USUARIOXANTECEDENTE ADD CONSTRAINT USUARIOXANTECEDENTE_PK PRIMARY KEY ( USUARIO_IDUSUARIO, ANTECEDENTE_IDANTECEDENTE ) ;


ALTER TABLE GINECOLOGICO ADD CONSTRAINT GINECOLOGICO_USUARIO_FK FOREIGN KEY ( USUARIO_IDUSUARIO ) REFERENCES USUARIO ( IDUSUARIO ) ;

ALTER TABLE HISTORIACLINICA ADD CONSTRAINT HISTORIACLINICA_U_FK FOREIGN KEY ( USUARIO_IDUSUARIO ) REFERENCES USUARIO ( IDUSUARIO ) ;

ALTER TABLE NIVELURGENCIA ADD CONSTRAINT NIVELURGENCIA_SINTOMA_FK FOREIGN KEY ( SINTOMA_IDSINTOMA ) REFERENCES SINTOMA ( IDSINTOMA ) ;

ALTER TABLE NIVELURGENCIA ADD CONSTRAINT NIVELURGENCIA_TRIAGE_FK FOREIGN KEY ( TRIAGE_IDTRIAGE ) REFERENCES TRIAGE ( IDTRIAGE ) ;

ALTER TABLE SINTOMASXHISTORIA ADD CONSTRAINT SINTOMASXHISTORIA_HC_FK FOREIGN KEY ( HISTORIACLINICA_IDHC ) REFERENCES HISTORIACLINICA ( IDHISTORIACLINICA ) ;

ALTER TABLE SINTOMASXHISTORIA ADD CONSTRAINT SINTOMASXHISTORIA_S_FK FOREIGN KEY ( SINTOMA_IDSINTOMA ) REFERENCES SINTOMA ( IDSINTOMA ) ;

ALTER TABLE USUARIOXANTECEDENTE ADD CONSTRAINT USUARIOXANTECEDENTE_A_FK FOREIGN KEY ( ANTECEDENTE_IDANTECEDENTE ) REFERENCES ANTECEDENTE ( IDANTECEDENTE ) ;

ALTER TABLE USUARIOXANTECEDENTE ADD CONSTRAINT USUARIOXANTECEDENTE_U_FK FOREIGN KEY ( USUARIO_IDUSUARIO ) REFERENCES USUARIO ( IDUSUARIO ) ;


-- Oracle SQL Developer Data Modeler Summary Report: 
-- 
-- CREATE TABLE                            10
-- CREATE INDEX                             0
-- ALTER TABLE                             19
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
