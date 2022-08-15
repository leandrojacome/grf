-- docker run --name oracle -d -p 1521:1521 -e DB_SID=XE -e DB_MEMORY=4G store/oracle/database-enterprise:12.2.0.1
-- docker exec -it oracle bash -c “source /home/oracle/.bashrc; sqlplus /nolog”
-- connect sys as sysdba;
-- sys
ALTER SESSION SET "_ORACLE_SCRIPT"=true;

CREATE USER GESTAO_RECURSOS_FINANCEIROS identified by GESTAO_RECURSOS_FINANCEIROS;
GRANT ALL PRIVILEGES TO GESTAO_RECURSOS_FINANCEIROS;

CREATE USER USER_GESTAO_RECURSOS_FINANCEIROS identified by USER_GESTAO_RECURSOS_FINANCEIROS;
GRANT ALL PRIVILEGES TO USER_GESTAO_RECURSOS_FINANCEIROS;

CREATE TABLESPACE GESTAO_RECURSOS_FINAN_DATA DATAFILE 'tbs_grfd.dbf' SIZE 1024m;
--DROP TABLESPACE GESTAO_RECURSOS_FINAN_DATA INCLUDING CONTENTS CASCADE CONSTRAINTS;