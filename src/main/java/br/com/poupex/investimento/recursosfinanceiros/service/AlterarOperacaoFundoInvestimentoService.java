package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.OperacaoFundoInvestimento;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.NegocioException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoFundosInvestimentosInputCadastrar;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.OperacaoFundosInvestimentosOutputDetalhe;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.OperacaoFundoInvestimentoRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.SQLIntegrityConstraintViolationException;

@Service
@RequiredArgsConstructor
public class AlterarOperacaoFundoInvestimentoService {

    private final ModelMapper mapper;
    private final ValidaOperacaoFundoInvestimentoService validaOperacaoFundoInvestimentoService;
    private final OperacaoFundoInvestimentoRepository operacaoFundoInvestimentoRepository;
    private final ManterSaldoFundoInvestimentoService manterSaldoFundoInvestimentoService;
    private final ObterOperacaoFundoInvestimentoService obterOperacaoFundoInvestimentoService;

    public ResponseModel execute(final String id, final OperacaoFundosInvestimentosInputCadastrar input) {

        var operacao = obterOperacaoFundoInvestimentoService.id(id);

        BeanUtils.copyProperties(mapper.map(validaOperacaoFundoInvestimentoService.execute(input),
                        OperacaoFundoInvestimento.class), operacao,
                "id", "boleta", "cadatro", "alteracao", "boleta");

        try {
            operacao = operacaoFundoInvestimentoRepository.save(operacao);
        } catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof ConstraintViolationException
                    && e.getCause().getCause() instanceof SQLIntegrityConstraintViolationException) {
                var msg = e.getCause().getCause().getMessage();

                throw new NegocioException("Alterar Operação Fundo de Investimento", msg);
            } else {
                throw new NegocioException("Alterar Operação Fundo de Investimento", "Não foi possível alterar a operação");
            }
        }

        return new ResponseModel(String.format("Operação de Fundo de Investimento cadastrada com sucesso! Boleta número: %s.", operacao.getBoleta()),
                mapper.map(operacao, OperacaoFundosInvestimentosOutputDetalhe.class)
        );
        
    }

    public static ManterSaldoFundoInvestimentoService manterSaldoFundoInvestimentoServiceSingleton;

    @PostConstruct
    public void init() {
        manterSaldoFundoInvestimentoServiceSingleton = manterSaldoFundoInvestimentoService;
    }
}
