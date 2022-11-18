package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.exception.EntidadeEmUsoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.IndicadorFinanceiroRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExcluirIndicadorFinanceiroService {

  private final ObterIndicadorFinanceiroService obterIndicadorFinanceiroService;

  private final ExcluirIndicadorFinanceiroTaxaService excluirIndicadorFinanceiroTaxaService;
  private final IndicadorFinanceiroRepository indicadorFinanceiroRepository;



  public ResponseModel execute(String id) {
    val indicadorFinanceiro = obterIndicadorFinanceiroService.id(id);
    try {
      try {
        excluirIndicadorFinanceiroTaxaService.indicadorFinanceiro(indicadorFinanceiro);
      } catch (final RecursoNaoEncontradoException ignored) {
      }
      indicadorFinanceiroRepository.delete(indicadorFinanceiro);
      indicadorFinanceiroRepository.flush();
    } catch (final EntidadeEmUsoException | DataIntegrityViolationException e) {
      throw new EntidadeEmUsoException("Indicador Financeiro");
    }
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      "Exclusão",
      String.format("Indicador Financeiro (%s) excluido com sucesso", id),
      "Indicador Financeiro excluido com sucesso",
      null,
      id
    );
  }

}
