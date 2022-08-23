CREATE TABLE GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA_ENDERECO
(
    ID                      VARCHAR2(36)    NOT NULL,
    CEP                     VARCHAR2(8)     NOT NULL,
    LOGRADOURO              VARCHAR2(512)   NOT NULL,
    NUMERO                  VARCHAR2(6)     ,
    COMPLEMENTO             VARCHAR2(256)   ,
    CIDADE                  VARCHAR2(32)    NOT NULL,
    UF                      VARCHAR2(4)     NOT NULL,
    CADASTRO                TIMESTAMP       NOT NULL,
    ATUALIZACAO             TIMESTAMP       NOT NULL,
    CONSTRAINT INSTITUICAO_FINANCEIRA_ENDERECO_PK   PRIMARY KEY (ID) ENABLE
) TABLESPACE GESTAO_RECURSOS_FINAN_DATA;
COMMENT
    ON TABLE GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA_ENDERECO
    IS 'Endereço da Instituição Financeira (INSTITUICAO_FINANCEIRA).';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA_ENDERECO.ID
    IS 'Identificador único.';
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


CREATE TABLE GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA
(
    ID                                      VARCHAR2(36)        NOT NULL,
    CNPJ                                    VARCHAR2(14)        NOT NULL,
    NOME                                    VARCHAR2(256)       NOT NULL,
    ABREVIACAO                              VARCHAR2(256)       NOT NULL,
    MATRIZ                                  NUMBER(1)           NOT NULL,
    GRUPO                                   VARCHAR2(36)        ,
    TIPO                                    VARCHAR2(64)        NOT NULL,
    SITE                                    VARCHAR2(1000)      ,
    CETIP                                   VARCHAR2(512)       ,
    CELIQ                                   VARCHAR2(512)       ,
    INSTITUICAO_FINANCEIRA_ENDERECO         VARCHAR2(36)        NOT NULL,
    CADASTRO                                TIMESTAMP           NOT NULL,
    ATUALIZACAO                             TIMESTAMP           NOT NULL,
    CONSTRAINT INSTITUICAO_FINANCEIRA_PK                                    PRIMARY KEY (ID) ENABLE,
    CONSTRAINT INSTITUICAO_FINANCEIRA_FK_INSTITUICAO_FINANCEIRA_GRUPO       FOREIGN KEY (GRUPO) REFERENCES GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA (ID),
    CONSTRAINT INSTITUICAO_FINANCEIRA_FK_INSTITUICAO_FINANCEIRA_ENDERECO    FOREIGN KEY (INSTITUICAO_FINANCEIRA_ENDERECO) REFERENCES GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA_ENDERECO (ID)

) TABLESPACE GESTAO_RECURSOS_FINAN_DATA;
COMMENT
    ON TABLE GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA
    IS 'Dados básicos das Instituições financeiras.';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA.ID
    IS 'Identificador único.';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA.CNPJ
    IS 'Número do CNPJ da instituição.';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA.NOME
    IS 'Nome completo (razão social ou nome fantasia) da instiuição.';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA.ABREVIACAO
    IS 'Abreviação da Instituição (Para combos ou resumos).';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA.MATRIZ
    IS 'Marcação se a Instituição é Matriz ou não (Para uso em GRUPO).';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA.GRUPO
    IS 'Grupo associado para a Instuição, somente para MATRIZ == false.';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA.TIPO
    IS 'Tipo da Instituição.';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA.SITE
    IS 'URL do site ou endereço eletronico da Instituição.';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA.CETIP
    IS 'Código CETIP da Instituição.';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA.CELIQ
    IS 'Código CELIQ da Instituição.';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA.INSTITUICAO_FINANCEIRA_ENDERECO
    IS 'Endereço da instituição.';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA.CADASTRO
    IS 'Data do cadastro da instituição.';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA.ATUALIZACAO
    IS 'Data da última atualização da Instituição.';
GRANT SELECT, INSERT, UPDATE, DELETE ON GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA TO USER_GESTAO_RECURSOS_FINANCEIROS;

