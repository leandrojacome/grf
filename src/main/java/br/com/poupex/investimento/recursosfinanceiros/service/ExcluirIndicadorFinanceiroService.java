package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.exception.EntidadeEmUsoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.IndicadorFinanceiroRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExcluirIndicadorFinanceiroService {

  private final ObterIndicadorFinanceiroService obterIndicadorFinanceiroService;
  private final IndicadorFinanceiroRepository indicadorFinanceiroRepository;

  public ResponseModel execute(String id) {
    obterIndicadorFinanceiroService.id(id);
    try {
      //deletar as taxas?
      indicadorFinanceiroRepository.deleteById(id);
      indicadorFinanceiroRepository.flush();
    } catch (final EntidadeEmUsoException | DataIntegrityViolationException e) {
      throw new RuntimeException(e);
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
