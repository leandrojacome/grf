 
CREATE TABLE GESTAO_RECURSOS_FINANCEIROS.INSTRUMENTO_FINANCEIRO
(
    ID                                  VARCHAR2(36)        NOT NULL,
    CADASTRO                            TIMESTAMP           NOT NULL,
    ATUALIZACAO                         TIMESTAMP           NOT NULL,
    CODIGO_GIF   NUMBER              NOT NULL,
    CONSTRAINT INSTRUMENTO_FINANCEIRO_PK                    PRIMARY KEY (ID) ENABLE,
    CONSTRAINT UNIQUE_CODIGO_GIF UNIQUE (CODIGO_GIF)
) TABLESPACE GESTAO_RECURSOS_FINAN_DATA;

COMMENT
ON TABLE GESTAO_RECURSOS_FINANCEIROS.INSTRUMENTO_FINANCEIRO
    IS 'Tabela responsável por armazenar os dados dos Instrumentos Financeiros.';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INSTRUMENTO_FINANCEIRO.ID
    IS 'Identificador único.';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INSTRUMENTO_FINANCEIRO.CADASTRO
    IS 'Data/Hora do cadastro do dado.';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INSTRUMENTO_FINANCEIRO.ATUALIZACAO
    IS 'Data/Hora da última atualização do dado.';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INSTRUMENTO_FINANCEIRO.CODIGO_GIF
    IS 'Vinculo com o registro do instrumento financeiro no GIF.';

GRANT SELECT, INSERT, UPDATE, DELETE ON GESTAO_RECURSOS_FINANCEIROS.INSTRUMENTO_FINANCEIRO TO USER_GESTAO_RECURSOS_FINANCEIROS;
