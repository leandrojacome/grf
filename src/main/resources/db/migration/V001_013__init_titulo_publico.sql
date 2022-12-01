CREATE TABLE GESTAO_RECURSOS_FINANCEIROS.TITULO_PUBLICO
(
    ID                                  VARCHAR2(36)    NOT NULL,
    CADASTRO                            TIMESTAMP       NOT NULL,
    ATUALIZACAO                         TIMESTAMP       NOT NULL,
    INSTRUMENTO_FINANCEIRO_GIF_CODIGO   NUMBER          NOT NULL,
    ISIN                                VARCHAR2(12)    NOT NULL,
    TIPO                                VARCHAR2(64)    NOT NULL,
    DATA_EMISSAO                        TIMESTAMP       NOT NULL,
    DATA_VENCIMENTO                     TIMESTAMP       NOT NULL,
    CODIGO_SELIC                        NUMBER(12)      NOT NULL,
    CUPOM                               NUMBER(1)       NOT NULL,
    CONSTRAINT TITULO_PUBLICO_PK                                       PRIMARY KEY (ID) ENABLE
) TABLESPACE GESTAO_RECURSOS_FINAN_DATA;
COMMENT
    ON TABLE GESTAO_RECURSOS_FINANCEIROS.TITULO_PUBLICO
    IS 'Titulos publicos (tabela complementada pela informacao_financeira no GIF)';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.TITULO_PUBLICO.ID
    IS 'Identificador único.';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.TITULO_PUBLICO.CADASTRO
    IS 'Data/Hora do cadastro do dado.';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.TITULO_PUBLICO.ATUALIZACAO
    IS 'Data/Hora da última atualização do dado.';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.TITULO_PUBLICO.INSTRUMENTO_FINANCEIRO_GIF_CODIGO
    IS 'Código de relacionamento do titulo publico com o registro do instrumento financeiro no GIF';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.TITULO_PUBLICO.ISIN
    IS 'Sigla isin';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.TITULO_PUBLICO.TIPO
    IS 'Tipo de mercado.';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.TITULO_PUBLICO.DATA_EMISSAO
    IS 'Data de emissao do titulo';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.TITULO_PUBLICO.DATA_VENCIMENTO
    IS 'Data de vencimento do titulo';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.TITULO_PUBLICO.CODIGO_SELIC
    IS 'Codigo da SELIC';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.TITULO_PUBLICO.CUPOM
    IS 'CUPOME';
GRANT SELECT, INSERT, UPDATE, DELETE ON GESTAO_RECURSOS_FINANCEIROS.TITULO_PUBLICO TO USER_GESTAO_RECURSOS_FINANCEIROS;
