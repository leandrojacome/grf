package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstituicaoFinanceira;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.InstituicaoFinanceiraRiscoClassificacao;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.NegocioException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.RiscoInput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.InstituicaoFinanceiraRiscoRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidaInstituicaoFinanceiraRiscosOpcoesService {

  private final InstituicaoFinanceiraRiscoRepository instituicaoFinanceiraRiscoRepository;

  public void execute(final String instituicao, final RiscoInput input) {
    val risco = instituicaoFinanceiraRiscoRepository.findOne(
      instituicaoFinanceiraRiscoRepository.instituicaoFinanceira(new InstituicaoFinanceira(instituicao))
        .and(instituicaoFinanceiraRiscoRepository.agenciaModalidade(input.getAgenciaModalidade()))
    );
    if (risco.isPresent()) {
      throw new NegocioException(
        "Risco já cadastrado", String.format("Já existe agencia/modalidade %s cadastrada na isntituição", input.getAgenciaModalidade()), null, input
      );
    }
    if (!InstituicaoFinanceiraRiscoClassificacao.findByAgenciaModalidade(input.getAgenciaModalidade()).contains(input.getClassificacao())) {
      throw new NegocioException(
        "Risco inválido",
        String.format("A Classificacao %s é inválida para a agência/modalidade %s.", input.getClassificacao(), input.getAgenciaModalidade()),
        null,
        input
      );
    }
  }

}
