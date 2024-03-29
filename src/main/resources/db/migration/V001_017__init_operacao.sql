CREATE TABLE GESTAO_RECURSOS_FINANCEIROS.OPERACAO
(
    ID              VARCHAR2(36)    NOT NULL,
    CADASTRO        TIMESTAMP       NOT NULL,
    ATUALIZACAO     TIMESTAMP       NOT NULL,
    NUMERO_BOLETA   VARCHAR2(36)    NOT NULL,
    TIPO            VARCHAR2(128)   NOT NULL,
    CONSTRAINT OPERACAO_PK  PRIMARY KEY (ID)    ENABLE,
    CONSTRAINT OPERACAO_NUMERO_BOLETA_UNIQUE    UNIQUE (NUMERO_BOLETA)
) TABLESPACE GESTAO_RECURSOS_FINAN_DATA;
GRANT SELECT, INSERT, UPDATE, DELETE ON GESTAO_RECURSOS_FINANCEIROS.OPERACAO TO USER_GESTAO_RECURSOS_FINANCEIROS;

COMMENT ON TABLE GESTAO_RECURSOS_FINANCEIROS.OPERACAO
    IS 'Tabela responsável por armazenar e compartilhar informações em comum entre as operações.';
COMMENT ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO.ID
    IS 'Identificador único.';
COMMENT ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO.CADASTRO
    IS 'Data/Hora do cadastro do dado.';
COMMENT ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO.ATUALIZACAO
    IS 'Data/Hora da última atualização do dado.';
COMMENT ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO.NUMERO_BOLETA
    IS 'Número da boleta gerado.';
