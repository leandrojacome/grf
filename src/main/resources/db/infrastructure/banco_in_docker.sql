/**
1. docker run --name oracle -d -p 1521:1521 -e ORACLE_PASSWORD=system -e ORACLE_DATABASE=oracle gvenzl/oracle-xe (leva alguns minutos)

2. abra o banco com as configs:
    url: jdbc:oracle:thin:@//localhost:1521/oracle
    username: system
    password: system

3.rode os scripts:
    CREATE USER GESTAO_RECURSOS_FINANCEIROS identified by docker;
    GRANT ALL PRIVILEGES TO GESTAO_RECURSOS_FINANCEIROS;
    CREATE USER USER_GESTAO_RECURSOS_FINANCEIROS identified by docker;
    GRANT ALL PRIVILEGES TO USER_GESTAO_RECURSOS_FINANCEIROS;
    CREATE TABLESPACE GESTAO_RECURSOS_FINAN_DATA DATAFILE 'tbs_grfd.dbf' SIZE 1024m;

4. Configura o SpringBoot pra aceitar os aprametros:
  spring.datasource.url=jdbc:oracle:thin:@//localhost:1521/oracle
  spring.datasource.password=docker
  spring.flyway.password=docker
 */



