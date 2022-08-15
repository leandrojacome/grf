CREATE TABLE GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA
(
    ID              VARCHAR2(36)    NOT NULL,
    CNPJ            VARCHAR2(14)    NOT NULL,
    NOME            VARCHAR2(256)   NOT NULL,
    ABREVIACAO      VARCHAR2(256)   NOT NULL,
    MATRIZ          NUMBER(1)       NOT NULL,
    GRUPO           VARCHAR2(36)    ,
    TIPO            VARCHAR2(64)    NOT NULL,
    SITE            VARCHAR2(1000)  ,
    CETIP_CODIGO    VARCHAR2(512)   ,
    CELIQ_CODIGO    VARCHAR2(512)   ,
    CELIQ_CONTA     VARCHAR2(512)   ,
    CADASTRO        TIMESTAMP       NOT NULL,
    ATUALIZACAO     TIMESTAMP       NOT NULL,
    CONSTRAINT INSTITUICAO_FINANCEIRA_PK PRIMARY KEY (ID) ENABLE,
    CONSTRAINT INSTITUICAO_FINANCEIRA_ENDERECO_FK_INSTITUICAO_FINANCEIRA_GRUPO FOREIGN KEY (GRUPO) REFERENCES GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA (ID)

) TABLESPACE GESTAO_RECURSOS_FINAN_DATA;

CREATE TABLE GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA_ENDERECO
(
    ID                      VARCHAR2(36)    NOT NULL,
    INSTITUICAO_FINANCEIRA  VARCHAR2(36)    NOT NULL,
    CEP                     VARCHAR2(8)     NOT NULL,
    LOGRADOURO              VARCHAR2(512)   NOT NULL,
    NUMERO                  VARCHAR2(6)     ,
    COMPLEMENTO             VARCHAR2(256)   ,
    CIDADE                  VARCHAR2(32)    NOT NULL,
    UF                      VARCHAR2(4)     NOT NULL,
    CADASTRO                TIMESTAMP       NOT NULL,
    ATUALIZACAO             TIMESTAMP       NOT NULL,
    CONSTRAINT INSTITUICAO_FINANCEIRA_ENDERECO_FK_INSTITUICAO_FINANCEIRA FOREIGN KEY (INSTITUICAO_FINANCEIRA) REFERENCES GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA (ID)
) TABLESPACE GESTAO_RECURSOS_FINAN_DATA;
