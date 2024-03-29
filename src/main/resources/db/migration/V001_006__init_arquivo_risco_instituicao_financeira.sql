CREATE TABLE GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA_RISCO_ARQUIVO
(
    ID                              VARCHAR2(36)    NOT NULL,
    INSTITUICAO_FINANCEIRA_RISCO    VARCHAR2(36)    NOT NULL,
    CADASTRO                        TIMESTAMP       NOT NULL,
    ATUALIZACAO                     TIMESTAMP       NOT NULL,
    NOME                            VARCHAR2(64)    NOT NULL,
    TIPO                            VARCHAR2(36)    NOT NULL,
    TAMANHO                         NUMBER          NOT NULL,
    CAMINHO                         VARCHAR2(1024)  NOT NULL,
    CONSTRAINT INSTITUICAO_FINANCEIRA_RISCO_ARQUIVO_PK  PRIMARY KEY (ID) ENABLE,
    CONSTRAINT INSTITUICAO_FINANCEIRA_RISCO_ARQUIVO_FK_INSTITUICAO_FINANCEIRA_RISCO FOREIGN KEY (INSTITUICAO_FINANCEIRA_RISCO) REFERENCES GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA_RISCO (ID)
) TABLESPACE GESTAO_RECURSOS_FINAN_DATA;
COMMENT
    ON TABLE GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA_RISCO_ARQUIVO
    IS 'Arquivo do risco da Instituição financeira.';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA_RISCO_ARQUIVO.ID
    IS 'Identificador único.';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA_RISCO_ARQUIVO.INSTITUICAO_FINANCEIRA_RISCO
    IS 'Vinculo com INSTITUICAO_FINANCEIRA_RISCO.';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA_RISCO_ARQUIVO.NOME
    IS 'Nome do arquivo.';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA_RISCO_ARQUIVO.TIPO
    IS 'Tipo do arquivo.';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA_RISCO_ARQUIVO.TAMANHO
    IS 'Tamanho do arquivo no momento do salvamento.';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA_RISCO_ARQUIVO.CAMINHO
    IS 'Caminho/Path do arquivo.';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA_RISCO_ARQUIVO.CADASTRO
    IS 'Data/Hora do cadastro do dado.';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA_RISCO_ARQUIVO.ATUALIZACAO
    IS 'Data/Hora da última atualização do dado.';
GRANT SELECT, INSERT, UPDATE, DELETE ON GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA_RISCO_ARQUIVO TO USER_GESTAO_RECURSOS_FINANCEIROS;
