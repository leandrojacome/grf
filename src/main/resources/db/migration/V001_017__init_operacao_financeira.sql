-- CREATE OPERACAO_FINANCEIRA --
CREATE TABLE GESTAO_RECURSOS_FINANCEIROS.OPERACAO_FINANCEIRA
(
    ID                                  VARCHAR2(36)        NOT NULL,
    CADASTRO                            TIMESTAMP           NOT NULL,
    ATUALIZACAO                         TIMESTAMP           NOT NULL,
    NUMERO_OPERACAO                     NUMBER              NOT NULL,
    INSTITUICAO_GIF_CODIGO              NUMBER              NOT NULL,
    OPERACAO_GIF_CODIGO                 NUMBER              NOT NULL,
    TIPO_MERCADO                        VARCHAR2(20)        NOT NULL,
    INSTRUMENTO_FINANCEIRO              VARCHAR2(36)        NOT NULL,
    INSTRUMENTO_FINANCEIRO_GRF_CODIGO   VARCHAR2(30)        NOT NULL,
    CODIGO_CUSTODIA_BB                  VARCHAR2(30)        NOT NULL,
    FORMA_MENSURACAO                    VARCHAR(60)         NOT NULL,
    DATA_EMISSAO                        TIMESTAMP           NOT NULL,
    DATA_LIQUIDACAO                     TIMESTAMP           NOT NULL,
    PRAZO_DC                            INTEGER             NOT NULL,
    PRAZO_DU                            INTEGER             NOT NULL,
    DATA_VENCIMENTO                     TIMESTAMP           NOT NULL,
    EMISSOR                             VARCHAR2(36)        NOT NULL,
    CONTRAPARTE                         VARCHAR2(36)        NOT NULL,
    TIPO_TAXA                           VARCHAR(10)         NOT NULL,
    TAXA                                DECIMAL(10,2)       NOT NULL,
    DIAS_UTEIS                          NUMBER(1)           NOT NULL,
    QTD_DIAS                            INTEGER             NOT NULL,
    PU_EMISSAO                          DECIMAL(10,2)       NOT NULL,
    VALOR_FINANCEIRO                    DECIMAL(10,2)       NOT NULL,
    VALOR_RESGATE                       DECIMAL(10,2)       NOT NULL,
    CUPOM                               NUMBER(1)           NOT NULL,
    PERIODO_CUPOM                       VARCHAR(10)         NOT NULL,
    DATA_PRIMEIRO_CUPOM                 TIMESTAMP           NOT NULL,
    OPERADOR_CONTRAPARTE                VARCHAR(100)        NOT NULL,
    VALOR_CORRETAGEM                    DECIMAL(10,2)       NOT NULL,
    CONSTRAINT OPERACAO_FINANCEIRA_PK                       PRIMARY KEY (ID) ENABLE,
    CONSTRAINT CHECK_DIAS_UTEIS                             CHECK ( DIAS_UTEIS = 0 OR DIAS_UTEIS = 1 ),
    CONSTRAINT CHECK_CUPOM                                  CHECK ( CUPOM = 0 OR CUPOM = 1),
    CONSTRAINT UNIQUE_NUMERO_OPERACAO                       UNIQUE ( NUMERO_OPERACAO ),
    CONSTRAINT OPERACAO_FINANCEIRA_EMISSOR_FK_INSTITUICAO_FINANCEIRA FOREIGN KEY (EMISSOR) REFERENCES GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA(ID),
    CONSTRAINT OPERACAO_FINANCEIRA_CONTRAPARTE_FK_INSTITUICAO_FINANCEIRA FOREIGN KEY (CONTRAPARTE) REFERENCES GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA(ID),
    CONSTRAINT OPERACAO_FINANCEIRA_INSTRUMENTO_FINANCEIRO_FK_INSTRUMENTO_FINANCEIRO FOREIGN KEY (INSTRUMENTO_FINANCEIRO) REFERENCES GESTAO_RECURSOS_FINANCEIROS.INSTRUMENTO_FINANCEIRO(ID)
) TABLESPACE GESTAO_RECURSOS_FINAN_DATA;

-- COMMENT --
COMMENT
ON TABLE GESTAO_RECURSOS_FINANCEIROS.OPERACAO_FINANCEIRA
    IS 'Tabela responsável por armazenar os dados das operações financeiras.';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_FINANCEIRA.ID
    IS 'Identificador único.';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_FINANCEIRA.CADASTRO
    IS 'Data/Hora do cadastro do dado.';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_FINANCEIRA.ATUALIZACAO
    IS 'Data/Hora da última atualização do dado.';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_FINANCEIRA.NUMERO_OPERACAO
    IS 'Número da operação.';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_FINANCEIRA.INSTITUICAO_GIF_CODIGO
    IS 'Código do registro da instituição na aplicação Gestão de Instrumentos Financeiros.';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_FINANCEIRA.OPERACAO_GIF_CODIGO
    IS 'Código do registro da operação na aplicação Gestão de Instrumentos Financeiros.';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_FINANCEIRA.TIPO_MERCADO
    IS 'Tipo do Mercado a qual o instrumento financeiro é enquadrado.';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_FINANCEIRA.INSTRUMENTO_FINANCEIRO
    IS 'Instrumento financeiro usado na operação';
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_FINANCEIRA.INSTRUMENTO_FINANCEIRO_GRF_CODIGO
    IS 'Código de controle interno do GRF para o instrumento financeiro.';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_FINANCEIRA.CODIGO_CUSTODIA_BB
    IS 'Código de custódia do Banco do Brasil';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_FINANCEIRA.FORMA_MENSURACAO
    IS 'Forma de mensuração da operação financeira';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_FINANCEIRA.DATA_EMISSAO
    IS 'Data da Emissão da operação financeira';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_FINANCEIRA.DATA_LIQUIDACAO
    IS 'Data da liquidação da operação financeira';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_FINANCEIRA.PRAZO_DC
    IS 'Prazo DC';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_FINANCEIRA.PRAZO_DU
    IS 'Prazo DU';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_FINANCEIRA.DATA_VENCIMENTO
    IS 'Data vencimento';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_FINANCEIRA.EMISSOR
    IS 'Instituição emissora do titulo financeiro';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_FINANCEIRA.CONTRAPARTE
    IS 'Instituição contraparte do titulo financeiro';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_FINANCEIRA.TIPO_TAXA
    IS 'Tipo de taxa';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_FINANCEIRA.TAXA
    IS 'Valor em percentual da taxa';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_FINANCEIRA.DIAS_UTEIS
    IS 'Indicação da forma de contagem do prazo em dias úteis ou dias corridos';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_FINANCEIRA.QTD_DIAS
    IS 'Quantidade de dias';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_FINANCEIRA.PU_EMISSAO
    IS 'Valor PU emissão';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_FINANCEIRA.VALOR_FINANCEIRO
    IS 'Valor financeiro';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_FINANCEIRA.VALOR_RESGATE
    IS 'Valor resgate';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_FINANCEIRA.CUPOM
    IS 'Indica verdadeiro (1) ou falso (0) para o registro do cupom';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_FINANCEIRA.PERIODO_CUPOM
    IS 'Período do cupom';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_FINANCEIRA.DATA_PRIMEIRO_CUPOM
    IS 'Data do primeiro cupom';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_FINANCEIRA.OPERADOR_CONTRAPARTE
    IS 'Operador contratande';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_FINANCEIRA.VALOR_CORRETAGEM
    IS 'Valor da corretagem';

-- GRANTS --
GRANT SELECT, INSERT, UPDATE, DELETE ON GESTAO_RECURSOS_FINANCEIROS.OPERACAO_FINANCEIRA TO USER_GESTAO_RECURSOS_FINANCEIROS;

CREATE SEQUENCE GESTAO_RECURSOS_FINANCEIROS.NUMERO_OPERACAO_SEQ MAXVALUE 9999;

GRANT SELECT ON GESTAO_RECURSOS_FINANCEIROS.NUMERO_OPERACAO_SEQ TO USER_GESTAO_RECURSOS_FINANCEIROS;


