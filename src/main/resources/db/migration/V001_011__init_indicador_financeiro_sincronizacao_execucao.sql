CREATE TABLE GESTAO_RECURSOS_FINANCEIROS.INDICADOR_FINANCEIRO_SINCRONIZACAO_EXECUCAO
(
    ID                                  VARCHAR2(36)    NOT NULL,
    CADASTRO                            TIMESTAMP       NOT NULL,
    ATUALIZACAO                         TIMESTAMP       NOT NULL,
    INDICADOR_FINANCEIRO_SINCRONIZACAO  VARCHAR2(36)    NOT NULL,
    REFERENCIA                          DATE            NOT NULL,
    PARAMETROS                          VARCHAR2(512)   NOT NULL,
    DADOS                               VARCHAR2(2048)  NOT NULL,
    SITUACAO                            VARCHAR2(36)    NOT NULL,
    CONSTRAINT INDICADOR_FINANCEIRO_SINCRONIZACAO_EXECUCAO_PK                                       PRIMARY KEY (ID) ENABLE,
    CONSTRAINT INDICADOR_FINANCEIRO_SINCRONIZACAO_EXECUCAO_FK_INDICADOR_FINANCEIRO_SINCRONIZACAO    FOREIGN KEY (INDICADOR_FINANCEIRO_SINCRONIZACAO) REFERENCES GESTAO_RECURSOS_FINANCEIROS.INDICADOR_FINANCEIRO_SINCRONIZACAO (ID),
    CONSTRAINT INDICADOR_FINANCEIRO_SINCRONIZACAO_EXECUCAO_UNIQUE                                   UNIQUE (INDICADOR_FINANCEIRO_SINCRONIZACAO, REFERENCIA, SITUACAO)
) TABLESPACE GESTAO_RECURSOS_FINAN_DATA;
COMMENT
    ON TABLE GESTAO_RECURSOS_FINANCEIROS.INDICADOR_FINANCEIRO_SINCRONIZACAO_EXECUCAO
    IS 'Execução e dados da sincronização das taxas do indicador financeiro.';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INDICADOR_FINANCEIRO_SINCRONIZACAO_EXECUCAO.ID
    IS 'Identificador único.';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INDICADOR_FINANCEIRO_SINCRONIZACAO_EXECUCAO.INDICADOR_FINANCEIRO_SINCRONIZACAO
    IS 'Vinculo com o indicador financeiro sincronizaçã (Configuração). INDICADOR_FINANCEIRO_SINCRONIZACAO.';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INDICADOR_FINANCEIRO_SINCRONIZACAO_EXECUCAO.REFERENCIA
    IS 'Data referencia da execução (se não houver uma imediatamente anterior é usado o limite 01/01/2000).';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INDICADOR_FINANCEIRO_SINCRONIZACAO_EXECUCAO.PARAMETROS
    IS 'Parametros enviados para o sistema externo (Path, auth, etc).';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INDICADOR_FINANCEIRO_SINCRONIZACAO_EXECUCAO.DADOS
    IS 'Dados da sincronização.';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INDICADOR_FINANCEIRO_SINCRONIZACAO_EXECUCAO.SITUACAO
    IS 'Situação da execução.';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INDICADOR_FINANCEIRO_SINCRONIZACAO_EXECUCAO.CADASTRO
    IS 'Data/Hora do cadastro do dado.';
COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INDICADOR_FINANCEIRO_SINCRONIZACAO_EXECUCAO.ATUALIZACAO
    IS 'Data/Hora da última atualização do dado.';
GRANT SELECT, INSERT, UPDATE, DELETE ON GESTAO_RECURSOS_FINANCEIROS.INDICADOR_FINANCEIRO_SINCRONIZACAO_EXECUCAO TO USER_GESTAO_RECURSOS_FINANCEIROS;
