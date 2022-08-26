CREATE TABLE GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA_CONTABIL
(
    ID                      VARCHAR2(36)    NOT NULL,
    CADASTRO                TIMESTAMP       NOT NULL,
    ATUALIZACAO             TIMESTAMP       NOT NULL,
    INSTITUICAO_FINANCEIRA  VARCHAR2(36)    NOT NULL,
    CONSTRAINT INSTITUICAO_FINANCEIRA_CONTABIL_PK                        PRIMARY KEY (ID) ENABLE,
    CONSTRAINT INSTITUICAO_FINANCEIRA_CONTABIL_FK_INSTITUICAO_FINANCEIRA FOREIGN KEY (INSTITUICAO_FINANCEIRA) REFERENCES GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA (ID)
) TABLESPACE GESTAO_RECURSOS_FINAN_DATA;
COMMENT
    ON TABLE GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA_CONTABIL
    IS 'Dados Contábeis da Instituição Financeira.';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA_CONTABIL.ID
    IS 'Identificador único.';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA_CONTABIL.INSTITUICAO_FINANCEIRA
    IS 'Vinculo da instituição financeira (INSTITUICAO_FINANCEIRA).';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA_CONTATO.NOME
    IS 'Nome do contato.';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA_CONTATO.EMAIL
    IS 'Endereço de email do contato.';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA_CONTATO.CARGO_SETOR
    IS 'Cargo e/ou setor do contato.';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA_CONTATO.TELEFONE1
    IS 'Número do contato (1).';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA_CONTATO.TELEFONE2
    IS 'Número do contato (2).';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA_CONTABIL.CADASTRO
    IS 'Data do cadastro do contato.';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA_CONTABIL.ATUALIZACAO
    IS 'Data da última atualização do contato.';
GRANT SELECT, INSERT, UPDATE, DELETE ON GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA_CONTABIL TO USER_GESTAO_RECURSOS_FINANCEIROS;
