CREATE TABLE GESTAO_RECURSOS_FINANCEIROS.TITULO_PRIVADO
(
    ID                                  VARCHAR(36)    NOT NULL,
    SIGLA                               VARCHAR(20)    NOT NULL,
    NOME                                VARCHAR(256)   NOT NULL,
    FORMA_MENSURACAO                    VARCHAR(60)    NOT NULL,
    CONSTRAINT TITULO_PRIVADO_PK PRIMARY KEY (ID) USING INDEX ENABLE,
    CONSTRAINT TITULO_PRIVADO_ID_FK_INSTRUMENTO_FINANCEIRO FOREIGN KEY (ID) REFERENCES GESTAO_RECURSOS_FINANCEIROS.INSTRUMENTO_FINANCEIRO(ID)
) TABLESPACE GESTAO_RECURSOS_FINAN_DATA;
COMMENT
    ON TABLE GESTAO_RECURSOS_FINANCEIROS.TITULO_PRIVADO
    IS 'Titulos publicos (tabela complementada pela informacao_financeira no GIF)';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.TITULO_PRIVADO.ID
    IS 'Identificador único.';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.TITULO_PRIVADO.SIGLA
    IS 'Sigla do Titulo Privado';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.TITULO_PRIVADO.NOME
    IS 'Nome do Titulo Privado';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.TITULO_PRIVADO.FORMA_MENSURACAO
    IS 'Forma de mensuração do instrumento financeiro Titulo Privado';

GRANT SELECT, INSERT, UPDATE, DELETE ON GESTAO_RECURSOS_FINANCEIROS.TITULO_PRIVADO TO USER_GESTAO_RECURSOS_FINANCEIROS;