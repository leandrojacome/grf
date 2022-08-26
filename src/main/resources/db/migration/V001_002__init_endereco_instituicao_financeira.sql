CREATE TABLE GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA_ENDERECO
(
    ID                      VARCHAR2(36)    NOT NULL,
    CADASTRO                TIMESTAMP       NOT NULL,
    ATUALIZACAO             TIMESTAMP       NOT NULL,
    INSTITUICAO_FINANCEIRA  VARCHAR2(36)    NOT NULL,
    CEP                     VARCHAR2(8)     NOT NULL,
    LOGRADOURO              VARCHAR2(512)   NOT NULL,
    NUMERO                  VARCHAR2(6)     ,
    COMPLEMENTO             VARCHAR2(256)   ,
    CIDADE                  VARCHAR2(32)    NOT NULL,
    UF                      VARCHAR2(4)     NOT NULL,
    CONSTRAINT INSTITUICAO_FINANCEIRA_ENDERECO_PK                               PRIMARY KEY (ID) ENABLE,
    CONSTRAINT INSTITUICAO_FINANCEIRA_ENDERECO_FK_INSTITUICAO_FINANCEIRA  FOREIGN KEY (INSTITUICAO_FINANCEIRA) REFERENCES GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA (ID)
) TABLESPACE GESTAO_RECURSOS_FINAN_DATA;
COMMENT
    ON TABLE GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA_ENDERECO
    IS 'Endereço da Instituição Financeira (INSTITUICAO_FINANCEIRA).';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA_ENDERECO.ID
    IS 'Identificador único.';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA_ENDERECO.INSTITUICAO_FINANCEIRA
    IS 'Vinculo da instituição financeira (INSTITUICAO_FINANCEIRA).';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA_ENDERECO.CEP
    IS 'CEP do endereço.';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA_ENDERECO.LOGRADOURO
    IS 'Logradouro do endereço.';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA_ENDERECO.NUMERO
    IS 'Número do endereço.';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA_ENDERECO.COMPLEMENTO
    IS 'Complemento do endereço.';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA_ENDERECO.CIDADE
    IS 'Cidade do endereço.';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA_ENDERECO.UF
    IS 'UF do endereço.';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA_ENDERECO.CADASTRO
    IS 'Data do cadastro do endereço.';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA_ENDERECO.ATUALIZACAO
    IS 'Data da última atualização do endereço.';
GRANT SELECT, INSERT, UPDATE, DELETE ON GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA_ENDERECO TO USER_GESTAO_RECURSOS_FINANCEIROS;
