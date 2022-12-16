-- CREATE FUNDOS DE INVESTIMENTOS --
CREATE TABLE GESTAO_RECURSOS_FINANCEIROS.FUNDOS_INVESTIMENTOS
(
    ID                                      VARCHAR2(36) NOT NULL,
    CADASTRO                                TIMESTAMP    NOT NULL,
    ATUALIZACAO                             TIMESTAMP    NOT NULL,
    CNPJ                                    VARCHAR(14)  NOT NULL,
    NOME                                    VARCHAR(256) NOT NULL,
    GESTOR                                  VARCHAR(256) NOT NULL,
    ADMINISTRADOR                           VARCHAR(256) NOT NULL,
    CLASSIFICACAO                           VARCHAR(256) NOT NULL,
    DATA_CONSTITUICAO                       TIMESTAMP    NOT NULL,
    DATA_COTIZACAO                          TIMESTAMP    NOT NULL,
    DATA_ASSEMBLEIA                         TIMESTAMP    NOT NULL,
    COTA                                    VARCHAR(20)  NOT NULL,
    PRAZO_COTIZACAO_APLICACAO               INTEGER      NOT NULL,
    DIAS_UTEIS_PRAZO_COTIZACAO_APLICACAO    NUMBER(1)    NOT NULL,
    PRAZO_COTIZACAO_RESGATE                 INTEGER      NOT NULL,
    DIAS_UTEIS_PRAZO_COTIZACAO_RESGATE      NUMBER(1)    NOT NULL,
    PRAZO_LIQ_FINANCEIRA                    INTEGER      NOT NULL,
    DIAS_UTEIS_PRAZO_LIQ_FINANCEIRA         NUMBER(1)    NOT NULL,
    INSTRUMENTO_FINANCEIRO_GIF_CODIGO       NUMBER       NOT NULL,
    ATIVO_FINANCEIRO                        NUMBER(1)    NOT NULL,
    FORMA_MENSURACAO                        VARCHAR(60)  NOT NULL,
    NIVEL                                   VARCHAR(60)  NOT NULL,
    SIGLA                                   VARCHAR(20)  NOT NULL,

    CONSTRAINT FUNDOS_INVESTIMENTOS_PK PRIMARY KEY (ID) ENABLE,
    CONSTRAINT CHECK_DIAS_UTEIS_PRAZO_COTIZACAO_APLICACAO CHECK ( DIAS_UTEIS_PRAZO_COTIZACAO_APLICACAO = 0 OR DIAS_UTEIS_PRAZO_COTIZACAO_APLICACAO = 1 ),
    CONSTRAINT CHECK_DIAS_UTEIS_PRAZO_COTIZACAO_RESGATE CHECK ( DIAS_UTEIS_PRAZO_COTIZACAO_RESGATE = 0 OR DIAS_UTEIS_PRAZO_COTIZACAO_RESGATE = 1 ),
    CONSTRAINT CHECK_DIAS_UTEIS_PRAZO_LIQ_FINANCEIRA CHECK ( DIAS_UTEIS_PRAZO_LIQ_FINANCEIRA = 0 OR DIAS_UTEIS_PRAZO_LIQ_FINANCEIRA = 1 ),
    CONSTRAINT CHECK_ATIVO_FINANCEIRO CHECK ( ATIVO_FINANCEIRO = 0 OR ATIVO_FINANCEIRO = 1 ),
    CONSTRAINT UNIQUE_INSTRUMENTO_FINANCEIRO_GIF_CODIGO UNIQUE (INSTRUMENTO_FINANCEIRO_GIF_CODIGO)
) TABLESPACE GESTAO_RECURSOS_FINAN_DATA;

-- GRANTS --
GRANT SELECT, INSERT, UPDATE, DELETE ON GESTAO_RECURSOS_FINANCEIROS.FUNDOS_INVESTIMENTOS TO USER_GESTAO_RECURSOS_FINANCEIROS;
