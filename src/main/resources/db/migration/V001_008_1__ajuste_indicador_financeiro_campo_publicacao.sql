ALTER TABLE GESTAO_RECURSOS_FINANCEIROS.INDICADOR_FINANCEIRO ADD PUBLICACAO VARCHAR2(64) DEFAULT 'DIAS_UTEIS' NOT NULL ;

COMMENT
    ON COLUMN GESTAO_RECURSOS_FINANCEIROS.INDICADOR_FINANCEIRO.PUBLICACAO
    IS 'Frequência da publicação da taxa.';
