CREATE TABLE GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_COMPROMISSADA
(
    ID                                  VARCHAR2(36)    NOT NULL,
    EMPRESA                             VARCHAR(36)     NOT NULL,
    CONTRAPARTE_INSTITUICAO_FINANCEIRA  VARCHAR(36)     NOT NULL,
    CONTRAPARTE_CONTA_SELIC             VARCHAR(64)     NOT NULL,
    CONTRAPARTE_NUMERO_BOLETA           NUMBER(12)      NOT NULL,
    CONTRAPARTE_OPERADOR                VARCHAR(256)    NOT NULL,
    DATA_IDA                            DATE            NOT NULL,
    DATA_VOLTA                          DATE            NOT NULL,
    TAXA_PRE                            NUMBER(30,8)    NOT NULL,
    TAXA_EFETIVA                        NUMBER(30,8)    NOT NULL,
    VALOR_ALVO                          NUMBER(30,8)    NOT NULL,
    FORMA_MENSURACAO                    VARCHAR(64)     NOT NULL,
    CUSTOS_VALOR_CORRETAGEM             NUMBER(30,8)    NOT NULL,
    CUSTOS_INDICADOR_FINANCEIRO         VARCHAR(36)     NOT NULL,
    CONSTRAINT OPERACAO_RENDA_FIXA_COMPROMISSADA_PK PRIMARY KEY (ID) ENABLE,
    CONSTRAINT OPERACAO_RENDA_FIXA_COMPROMISSADA_FK_INSTITUICAO_FINANCEIRA  FOREIGN KEY (CONTRAPARTE_INSTITUICAO_FINANCEIRA)
        REFERENCES GESTAO_RECURSOS_FINANCEIROS.INSTITUICAO_FINANCEIRA (ID),
    CONSTRAINT OPERACAO_RENDA_FIXA_COMPROMISSADA_FK_INDICADOR_FINANCEIRO    FOREIGN KEY (CUSTOS_INDICADOR_FINANCEIRO)
        REFERENCES GESTAO_RECURSOS_FINANCEIROS.INDICADOR_FINANCEIRO (ID)
) TABLESPACE GESTAO_RECURSOS_FINAN_DATA;
GRANT SELECT, INSERT, UPDATE, DELETE ON GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_COMPROMISSADA TO USER_GESTAO_RECURSOS_FINANCEIROS;

COMMENT ON TABLE GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_COMPROMISSADA
    IS 'Tabela responsável por armazenar os dados das operações financeiras - Renda Fixa Compromissada.';
COMMENT ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_COMPROMISSADA.ID
    IS 'Identificador único.';
COMMENT ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_COMPROMISSADA.EMPRESA
    IS 'Empresa da operação (Inicial FHE ou POUPEX, Dominio: Empresa).';
COMMENT ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_COMPROMISSADA.CONTRAPARTE_INSTITUICAO_FINANCEIRA
    IS 'Contraparte: Relacionamento Intituição financeira.';
COMMENT ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_COMPROMISSADA.CONTRAPARTE_CONTA_SELIC
    IS 'Contraparte: Conta SELIC da Instituição Financeira.';
COMMENT ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_COMPROMISSADA.CONTRAPARTE_NUMERO_BOLETA
    IS 'Contraparte: Número da boleta da contraparte.';
COMMENT ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_COMPROMISSADA.CONTRAPARTE_OPERADOR
    IS 'Contraparte: Nome do operador';
COMMENT ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_COMPROMISSADA.DATA_IDA
    IS 'Data de ida da operação.';
COMMENT ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_COMPROMISSADA.DATA_VOLTA
    IS 'Data de volta da operação.';
COMMENT ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_COMPROMISSADA.TAXA_PRE
    IS 'Taxa pré definida da operação.';
COMMENT ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_COMPROMISSADA.TAXA_EFETIVA
    IS 'Taxa efetiva da operação.';
COMMENT ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_COMPROMISSADA.VALOR_ALVO
    IS 'Valor de alvo da operação.';
COMMENT ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_COMPROMISSADA.FORMA_MENSURACAO
    IS 'Forma de mensuração da operação Dominio: FormaMensuracaoEnum.';
COMMENT ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_COMPROMISSADA.CUSTOS_VALOR_CORRETAGEM
    IS 'Valor da corretagem da operação.';
COMMENT ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_COMPROMISSADA.CUSTOS_INDICADOR_FINANCEIRO
    IS 'Custos: Indicador financeiro atrelado ao custo.';

CREATE TABLE GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_COMPROMISSADA_LASTRO
(
    ID                                  VARCHAR2(36)    NOT NULL,
    CADASTRO                            TIMESTAMP       NOT NULL,
    ATUALIZACAO                         TIMESTAMP       NOT NULL,
    OPERACAO_RENDA_FIXA_COMPROMISSADA   VARCHAR(36)     NOT NULL,
    INSTRUMENTO_FINANCEIRO              VARCHAR(36)     NOT NULL,
    QUANTIDADE                          NUMBER(15)      NOT NULL,
    PRECO_UNITARIO_IDA                  NUMBER(30,8)    NOT NULL,
    PRECO_UNITARIO_VOLTA                NUMBER(30,8)    NOT NULL,
    VALOR_FINANCEIRO_IDA                NUMBER(30,8)    NOT NULL,
    VALOR_FINANCEIRO_VOLTA              NUMBER(30,8)    NOT NULL,
    CONSTRAINT OPERACAO_RENDA_FIXA_COMPROMISSADA_LASTRO_PK                                      PRIMARY KEY (ID) ENABLE,
    CONSTRAINT OPERACAO_RENDA_FIXA_COMPROMISSADA_LASTRO_FK_OPERACAO_RENDA_FIXA_COMPROMISSADA    FOREIGN KEY (OPERACAO_RENDA_FIXA_COMPROMISSADA)
        REFERENCES GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_COMPROMISSADA (ID)

) TABLESPACE GESTAO_RECURSOS_FINAN_DATA;
GRANT SELECT, INSERT, UPDATE, DELETE ON GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_COMPROMISSADA_LASTRO TO USER_GESTAO_RECURSOS_FINANCEIROS;

COMMENT ON TABLE GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_COMPROMISSADA_LASTRO
    IS 'Tabela responsável por armazenar os dados dos lastros das operações financeiras - Renda Fixa Compromissada.';
COMMENT ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_COMPROMISSADA_LASTRO.ID
    IS 'Identificador único.';
COMMENT ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_COMPROMISSADA_LASTRO.CADASTRO
    IS 'Data/Hora do cadastro do dado.';
COMMENT ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_COMPROMISSADA_LASTRO.ATUALIZACAO
    IS 'Data/Hora da última atualização do dado.';
COMMENT ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_COMPROMISSADA_LASTRO.OPERACAO_RENDA_FIXA_COMPROMISSADA
    IS 'Vinculo com a operação compromissada: tabela OPERACAO_RENDA_FIXA_COMPROMISSADA.';
COMMENT ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_COMPROMISSADA_LASTRO.INSTRUMENTO_FINANCEIRO
    IS 'Vinculo do instrimento utilizado no lastro. Relacionamento tabela INSTRUMENTO_FINANCEIRO.';
COMMENT ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_COMPROMISSADA_LASTRO.QUANTIDADE
    IS 'Quantidade de itens do lastro.';
COMMENT ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_COMPROMISSADA_LASTRO.PRECO_UNITARIO_IDA
    IS 'Preço unitário dos itens da ida.';
COMMENT ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_COMPROMISSADA_LASTRO.PRECO_UNITARIO_VOLTA
    IS 'Preço unitário dos itens da volta.';
COMMENT ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_COMPROMISSADA_LASTRO.VALOR_FINANCEIRO_IDA
    IS 'Valor financeiro calulado da ida.';
COMMENT ON COLUMN GESTAO_RECURSOS_FINANCEIROS.OPERACAO_RENDA_FIXA_COMPROMISSADA_LASTRO.VALOR_FINANCEIRO_VOLTA
    IS 'Valor financeiro calulado da volta.';

