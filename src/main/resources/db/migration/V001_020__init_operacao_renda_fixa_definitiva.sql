-- CREATE OPERACAO_RENDA_FIXA_DEFINITIVA --
CREATE TABLE GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_DEFINITIVA
(
    ID                                  VARCHAR2(36)        NOT NULL,
    EMPRESA                             VARCHAR2(30)        NOT NULL,
    OPERACAO_GIF_CODIGO                 NUMBER              NOT NULL,
    TIPO_MERCADO                        VARCHAR2(20)        NOT NULL,
    INSTRUMENTO_FINANCEIRO              VARCHAR2(36)        NOT NULL,
    CODIGO_IF_GRF                       VARCHAR2(30)        NOT NULL,
    CODIGO_CUSTODIA_BB                  VARCHAR2(30)        NOT NULL,
    FORMA_MENSURACAO                    VARCHAR(60)         NOT NULL,
    DATA_EMISSAO                        TIMESTAMP           NOT NULL,
    DATA_LIQUIDACAO                     TIMESTAMP           NOT NULL,
    DATA_COMPRA                         TIMESTAMP           NOT NULL,
    PRAZO_DC                            INTEGER             NOT NULL,
    PRAZO_DU                            INTEGER             NOT NULL,
    DATA_VENCIMENTO                     TIMESTAMP           NOT NULL,
    EMISSOR                             VARCHAR2(36)        NOT NULL,
    CONTRAPARTE                         VARCHAR2(36)        NOT NULL,
    QTD_DIAS                            INTEGER             NOT NULL,
    VALOR_FINANCEIRO                    DECIMAL(10,2)       NOT NULL,
    CUPOM                               NUMBER(1)           NOT NULL,
    PERIODO_CUPOM                       VARCHAR(10)         NOT NULL,
    DATA_PRIMEIRO_CUPOM                 TIMESTAMP           NOT NULL,
    OPERADOR_CONTRAPARTE                VARCHAR(100)        NOT NULL,
    VALOR_CORRETAGEM                    DECIMAL(10,2)       NOT NULL,
    CUSTO_OPERACAO                      VARCHAR2(36)        NOT NULL,
    CONSTRAINT OPERACAO_RENDA_FIXA_DEFINITIVA_PK            PRIMARY KEY (ID) ENABLE,
    CONSTRAINT OPERACAO_RENDA_FIXA_DEFINITIVA_FK_OPERACAO  FOREIGN KEY (ID)
        REFERENCES GESTAO_RECURSOS_FINANCEIROS.OPERACAO (ID),
    CONSTRAINT CHECK_CUPOM                                  CHECK ( CUPOM = 0 OR CUPOM = 1),
    CONSTRAINT OPERACAO_RENDA_FIXA_DEFINITIVA_EMISSOR_FK_INSTITUICAO_FINANCEIRA FOREIGN KEY (EMISSOR) REFERENCES GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA(ID),
    CONSTRAINT OPERACAO_RENDA_FIXA_DEFINITIVA_CONTRAPARTE_FK_INSTITUICAO_FINANCEIRA FOREIGN KEY (CONTRAPARTE) REFERENCES GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA(ID),
    CONSTRAINT OPERACAO_RENDA_FIXA_DEFINITIVA_INSTRUMENTO_FINANCEIRO_FK_INSTRUMENTO_FINANCEIRO FOREIGN KEY (INSTRUMENTO_FINANCEIRO) REFERENCES GESTAO_RECURSOS_FINANCEIROS.INSTRUMENTO_FINANCEIRO(ID),
    CONSTRAINT OPERACAO_RENDA_FIXA_DEFINITIVA_CUSTO_OPERACAO_FK_INDICADOR_FINANCEIRO FOREIGN KEY (CUSTO_OPERACAO) REFERENCES GESTAO_RECURSOS_FINANCEIROS.INDICADOR_FINANCEIRO(ID),
) TABLESPACE GESTAO_RECURSOS_FINAN_DATA;

CREATE TABLE GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_DEFINITIVA_PRIMARIO
(
    ID                                  VARCHAR2(36)        NOT NULL,
    TIPO_TAXA                           VARCHAR(10)         NOT NULL,
    TAXA_PRE                            DECIMAL(10,2)       NULL,
    DIAS_UTEIS                          NUMBER(1)           NOT NULL,
    TAXA_EFETIVA                        DECIMAL(10,2)       NULL,
    INDICE                              VARCHAR2(36)        NULL,
    PERCENTUAL_INDICE                   DECIMAL(10,2)       NULL,
    PU_EMISSAO                          DECIMAL(10,2)       NOT NULL,
    VALOR_RESGATE                       DECIMAL(10,2)       NOT NULL,
    CONSTRAINT OPERACAO_RENDA_FIXA_DEFINITIVA_PRIMARIO_PK            PRIMARY KEY (ID) ENABLE,
    CONSTRAINT OPERACAO_RENDA_FIXA_DEFINITIVA_PRIMARIO_FK_OPERACAO_RENDA_FIXA_DEFINITIVA  FOREIGN KEY (ID)
        REFERENCES GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_DEFINITIVA (ID),
    CONSTRAINT OPERACAO_RENDA_FIXA_DEFINITIVA_PRIMARIO_INDICE_FK_INDICADOR_FINANCEIRO FOREIGN KEY (INDICE) REFERENCES GESTAO_RECURSOS_FINANCEIROS.INDICADOR_FINANCEIRO(ID),
    CONSTRAINT CHECK_DIAS_UTEIS        CHECK ( DIAS_UTEIS = 0 OR DIAS_UTEIS = 1 ),
    CONSTRAINT CHECK_TAXA_PRE          CHECK (TIPO_TAXA = 'POS' OR (TIPO_TAXA IN('PRE','PRE_POS') AND TAXA_PRE IS NOT NULL)),
    CONSTRAINT CHECK_TAXA_EFETIVA      CHECK (TIPO_TAXA = 'POS' OR (TIPO_TAXA IN('PRE','PRE_POS') AND TAXA_EFETIVA IS NOT NULL)),
    CONSTRAINT CHECK_INDICE            CHECK (TIPO_TAXA = 'PRE' OR (TIPO_TAXA IN('POS','PRE_POS') AND INDICE IS NOT NULL)),
    CONSTRAINT CHECK_PERCENTUAL_INDICE CHECK (TIPO_TAXA = 'PRE' OR (TIPO_TAXA IN('POS','PRE_POS') AND PERCENTUAL_INDICE IS NOT NULL)),
) TABLESPACE GESTAO_RECURSOS_FINAN_DATA;

CREATE TABLE GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_DEFINITIVA_SECUNDARIO
(
    ID                                  VARCHAR2(36)        NOT NULL,
    TIPO_TAXA                           VARCHAR(10)         NOT NULL,
    TAXA_PRE                            DECIMAL(10,2)       NULL,
    DIAS_UTEIS_TAXA_PRE                 NUMBER(1)           NULL,
    TAXA_EFETIVA                        DECIMAL(10,2)       NULL,
    TAXA_NEGOCIACAO                     DECIMAL(10,2)       NULL,
    TAXA_PRE_NEGOCIACAO                 DECIMAL(10,2)       NULL,
    DIAS_UTEIS_TAXA_PRE_NEGOCIACAO      NUMBER(1)           NULL,
    INDICE                              VARCHAR2(36)        NULL,
    PERCENTUAL_INDICE                   DECIMAL(10,2)       NULL,
    DIAS_UTEIS_INDICE                   NUMBER(1)           NULL,
    INDICE_NEGOCIACAO                   VARCHAR2(36)        NULL,
    PERCENTUAL_NEGOCIACAO               DECIMAL(10,2)       NULL,
    DIAS_UTEIS_INDICE_NEGOCIACAO        NUMBER(1)           NULL,
    PU_AGIO_DESAGIO                     DECIMAL(10,2)       NOT NULL,
    PU_ATUAL                            DECIMAL(10,2)       NOT NULL,
    PU_NEGOCIADO                        DECIMAL(10,2)       NOT NULL,
    PU_POUPEX                           DECIMAL(10,2)       NOT NULL,
    PU_CONTRAPARTE                      DECIMAL(10,2)       NOT NULL,
    VALOR_FINANCEIRO_AGIO_DESAGIO       DECIMAL(10,2)       NOT NULL,
    VALOR_FINANCEIRO_NEGOCIACAO         DECIMAL(10,2)       NOT NULL,
    VALOR_FINANCEIRO_ATUAL              DECIMAL(10,2)       NOT NULL,
    VALOR_FINANCEIRO_NEGOCIADO          DECIMAL(10,2)       NOT NULL,
    VALOR_FINANCEIRO_POUPEX             DECIMAL(10,2)       NOT NULL,
    VALOR_FINANCEIRO_CONTRAPARTE        DECIMAL(10,2)       NOT NULL,
    CONSTRAINT OPERACAO_RENDA_FIXA_DEFINITIVA_SECUNDARIO_PK            PRIMARY KEY (ID) ENABLE,
    CONSTRAINT OPERACAO_RENDA_FIXA_DEFINITIVA_SECUNDARIO_FK_OPERACAO_RENDA_FIXA_DEFINITIVA  FOREIGN KEY (ID)
        REFERENCES GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_DEFINITIVA (ID),
    CONSTRAINT OPERACAO_RENDA_FIXA_DEFINITIVA_SECUNDARIO_INDICE_FK_INDICADOR_FINANCEIRO FOREIGN KEY (INDICE) REFERENCES GESTAO_RECURSOS_FINANCEIROS.INDICADOR_FINANCEIRO(ID),
    CONSTRAINT OPERACAO_RENDA_FIXA_DEFINITIVA_SECUNDARIO_INDICE_NEGOCIACAO_FK_INDICADOR_FINANCEIRO FOREIGN KEY (INDICE_NEGOCIACAO) REFERENCES GESTAO_RECURSOS_FINANCEIROS.INDICADOR_FINANCEIRO(ID),
    CONSTRAINT CHECK_TAXA_PRE               CHECK (TIPO_TAXA = 'POS' OR (TIPO_TAXA IN('PRE','PRE_POS') AND TAXA_PRE IS NOT NULL)),
    CONSTRAINT CHECK_DIAS_UTEIS_TAXA_PRE    CHECK (TIPO_TAXA = 'POS' OR (TIPO_TAXA IN('PRE','PRE_POS') AND ( DIAS_UTEIS_TAXA_PRE = 0 OR DIAS_UTEIS_TAXA_PRE = 1 ))),
    CONSTRAINT CHECK_TAXA_EFETIVA           CHECK (TIPO_TAXA = 'POS' OR (TIPO_TAXA IN('PRE','PRE_POS') AND TAXA_EFETIVA IS NOT NULL)),
    CONSTRAINT CHECK_TAXA_NEGOCIACAO        CHECK (TIPO_TAXA = 'POS' OR (TIPO_TAXA IN('PRE','PRE_POS') AND TAXA_NEGOCIACAO IS NOT NULL)),
    CONSTRAINT CHECK_TAXA_PRE_NEGOCIACAO    CHECK (TIPO_TAXA = 'POS' OR (TIPO_TAXA IN('PRE','PRE_POS') AND TAXA_PRE_NEGOCIACAO IS NOT NULL)),
    CONSTRAINT CHECK_DIAS_UTEIS_TAXA_PRE_NEGOCIACAO CHECK (TIPO_TAXA = 'POS' OR (TIPO_TAXA IN('PRE','PRE_POS') AND ( DIAS_UTEIS_TAXA_PRE_NEGOCIACAO = 0 OR DIAS_UTEIS_TAXA_PRE_NEGOCIACAO = 1 ))),
    CONSTRAINT CHECK_INDICE                 CHECK (TIPO_TAXA = 'PRE' OR (TIPO_TAXA IN('POS','PRE_POS') AND INDICE IS NOT NULL)),
    CONSTRAINT CHECK_PERCENTUAL_INDICE      CHECK (TIPO_TAXA = 'PRE' OR (TIPO_TAXA IN('POS','PRE_POS') AND PERCENTUAL_INDICE IS NOT NULL)),
    CONSTRAINT CHECK_DIAS_UTEIS_INDICE      CHECK (TIPO_TAXA = 'PRE' OR (TIPO_TAXA IN('POS','PRE_POS') AND ( DIAS_UTEIS_INDICE = 0 OR DIAS_UTEIS_INDICE = 1 ))),
    CONSTRAINT CHECK_INDICE_NEGOCIACAO      CHECK (TIPO_TAXA = 'PRE' OR (TIPO_TAXA IN('POS','PRE_POS') AND INDICE_NEGOCIACAO IS NOT NULL)),
    CONSTRAINT CHECK_PERCENTUAL_NEGOCIACAO  CHECK (TIPO_TAXA = 'PRE' OR (TIPO_TAXA IN('POS','PRE_POS') AND PERCENTUAL_NEGOCIACAO IS NOT NULL)),
    CONSTRAINT CHECK_DIAS_UTEIS_INDICE_NEGOCIACAO CHECK (TIPO_TAXA = 'PRE' OR (TIPO_TAXA IN('POS','PRE_POS') AND ( DIAS_UTEIS_INDICE_NEGOCIACAO = 0 OR DIAS_UTEIS_INDICE_NEGOCIACAO = 1 ))),
) TABLESPACE GESTAO_RECURSOS_FINAN_DATA;

-- COMMENT --
COMMENT
ON TABLE GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_DEFINITIVA
    IS 'Tabela responsável por armazenar os dados das operações financeiras.';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_DEFINITIVA.ID
    IS 'Identificador único.';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_DEFINITIVA.EMPRESA
    IS 'Empresa responsável pela operação.';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_DEFINITIVA.OPERACAO_GIF_CODIGO
    IS 'Código do registro da operação na aplicação Gestão de Instrumentos Financeiros.';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_DEFINITIVA.TIPO_MERCADO
    IS 'Tipo do Mercado a qual o instrumento financeiro é enquadrado.';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_DEFINITIVA.INSTRUMENTO_FINANCEIRO
    IS 'Instrumento financeiro usado na operação';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_DEFINITIVA.CODIGO_IF_GRF
    IS 'Código de controle interno do GRF para o instrumento financeiro.';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_DEFINITIVA.CODIGO_CUSTODIA_BB
    IS 'Código de custódia do Banco do Brasil';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_DEFINITIVA.FORMA_MENSURACAO
    IS 'Forma de mensuração da Operação Renda Fixa Definitiva';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_DEFINITIVA.DATA_EMISSAO
    IS 'Data da Emissão da Operação Renda Fixa Definitiva';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_DEFINITIVA.DATA_LIQUIDACAO
    IS 'Data da liquidação da Operação Renda Fixa Definitiva';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_DEFINITIVA.DATA_COMPRA
    IS 'Data da compra da Operação Renda Fixa Definitiva';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_DEFINITIVA.PRAZO_DC
    IS 'Prazo DC';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_DEFINITIVA.PRAZO_DU
    IS 'Prazo DU';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_DEFINITIVA.DATA_VENCIMENTO
    IS 'Data vencimento';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_DEFINITIVA.EMISSOR
    IS 'Instituição emissora do titulo financeiro';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_DEFINITIVA.CONTRAPARTE
    IS 'Instituição contraparte do titulo financeiro';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_DEFINITIVA.TIPO_TAXA
    IS 'Tipo de taxa';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_DEFINITIVA.TAXA
    IS 'Valor em percentual da taxa, no mercado secundario "taxa de emissao", valido para TIPO_TAXA igual a PRE';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_DEFINITIVA.TAXA_EFETIVA
    IS 'Valor em percentual da taxa efetiva, no mercado secundario "taxa efetiva de emissao", valido para TIPO_TAXA igual a PRE';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_DEFINITIVA.TAXA_PRE
    IS 'Valor em percentual da taxa pre de emissao, valido para o mercado secundario, valido para TIPO_TAXA igual a PRE ou PRE-POS';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_DEFINITIVA.TAXA_NEGOCIACAO
    IS 'Valor em percentual da taxa de negociacao, valido para o mercado secundario, valido para TIPO_TAXA igual a PRE';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_DEFINITIVA.TAXA_PRE_NEGOCIACAO
    IS 'Valor em percentual da taxa efetiva de negociacao, valido para o mercado secundario, valido para TIPO_TAXA igual a PRE';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_DEFINITIVA.TAXA_PRE_NEGOCIACAO
    IS 'Valor em percentual da taxa pre de negociacao, valido para o mercado secundario, valido para TIPO_TAXA igual a PRE ou PRE-POS';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_DEFINITIVA.INDICE
    IS 'Indice (Indicador Financeiro), no mercado secundario "indice de emissao", valido para TIPO_TAXA igual a POS ou PRE-POS';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_DEFINITIVA.INDICE_NEGOCIACAO
    IS 'Indice de negociacao (Indicador Financeiro), valido para o mercado secundario, valido para TIPO_TAXA igual a POS ou PRE-POS';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_DEFINITIVA.PERCENTUAL_INDICE
    IS 'Valor em percentual do campo indice, no mercado secundario "percentual de emissao", valido para TIPO_TAXA igual a POS ou PRE-POS';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_DEFINITIVA.PERCENTUAL_NEGOCIACAO
    IS 'Valor em percentual do campo indice de negociacao, valido para o mercado secundario, valido para TIPO_TAXA igual a POS ou PRE-POS';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_DEFINITIVA.DIAS_UTEIS
    IS 'Indicação da forma de contagem do prazo em dias úteis ou dias corridos';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_DEFINITIVA.QTD_DIAS
    IS 'Quantidade de dias';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_DEFINITIVA.PU_EMISSAO
    IS 'Valor PU emissão, no mercado secundario "pu atual"';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_DEFINITIVA.VALOR_FINANCEIRO
    IS 'Valor financeiro, no mercado secundario "valor financeiro atual"';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_DEFINITIVA.VALOR_FINANCEIRO_AGIO_DESAGIO
    IS 'Valor financeiro agio desagio, valido para o mercado secundario';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_DEFINITIVA.VALOR_FINANCEIRO_NEGOCIACAO
    IS 'Valor financeiro de negociacao, valido para o mercado secundario';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_DEFINITIVA.VALOR_RESGATE
    IS 'Valor resgate, valido para TIPO_MERCADO igual a MERCADO_PRIMARIO';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_DEFINITIVA.CUPOM
    IS 'Indica verdadeiro (1) ou falso (0) para o registro do cupom';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_DEFINITIVA.PERIODO_CUPOM
    IS 'Período do cupom';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_DEFINITIVA.DATA_PRIMEIRO_CUPOM
    IS 'Data do primeiro cupom';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_DEFINITIVA.OPERADOR_CONTRAPARTE
    IS 'Operador contratande';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_DEFINITIVA.VALOR_CORRETAGEM
    IS 'Valor da corretagem';
COMMENT
ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_DEFINITIVA.CUSTO_OPERACAO
    IS 'Custo da operação';

-- GRANTS --
GRANT SELECT, INSERT, UPDATE, DELETE ON GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_DEFINITIVA TO USER_GESTAO_RECURSOS_FINANCEIROS;


