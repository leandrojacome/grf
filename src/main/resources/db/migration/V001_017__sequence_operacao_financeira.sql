 
CREATE SEQUENCE GESTAO_RECURSOS_FINANCEIROS.NUMERO_OPERACAO_SEQ MAXVALUE 9999;

GRANT SELECT ON GESTAO_RECURSOS_FINANCEIROS.NUMERO_OPERACAO_SEQ TO USER_GESTAO_RECURSOS_FINANCEIROS;

UPDATE GESTAO_RECURSOS_FINANCEIROS.OPERACAO_FINANCEIRA
	SET NUMERO_OPERACAO = TO_NUMBER(SUBSTR(TO_CHAR(NUMERO_OPERACAO), 1, 8) || LPAD(TO_CHAR(GESTAO_RECURSOS_FINANCEIROS.NUMERO_OPERACAO_SEQ.nextval), 4, '0'));


