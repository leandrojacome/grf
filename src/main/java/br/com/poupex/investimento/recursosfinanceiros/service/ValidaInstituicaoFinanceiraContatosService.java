package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstituicaoFinanceira;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.NegocioException;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.InstituicaoFinanceiraContatoRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidaInstituicaoFinanceiraContatosService {

  private final ObterInstituicaoFinanceiraService obterInstituicaoFinanceiraService;
  private final InstituicaoFinanceiraContatoRepository instituicaoFinanceiraContatoRepository;

  public InstituicaoFinanceira execute(final String idInstituicaoFinanceira) {
    val instituicaoFinanceira = obterInstituicaoFinanceiraService.id(idInstituicaoFinanceira);
    if (instituicaoFinanceiraContatoRepository.count(instituicaoFinanceiraContatoRepository.instituicaoFinanceira(instituicaoFinanceira)) >= 3) {
      throw new NegocioException("Limite contatos Instituição Financeira", "Só é possível salvar até 3 contatos por Instituição Financeira");
    }
    return instituicaoFinanceira;
  }

}
