package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstituicaoFinanceira;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.InstituicaoFinanceiraRiscoCategoria;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.InstituicaoFinanceiraRiscoCategoriaOpcao;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.NegocioException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.RiscoInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ValidacaoModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.InstituicaoFinanceiraRiscoRepository;
import java.util.ArrayList;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidaInstituicaoFinanceiraRiscosOpcoesService {

  private final InstituicaoFinanceiraRiscoRepository instituicaoFinanceiraRiscoRepository;

  public void execute(final String instituicao, final RiscoInputOutput input) {
    val risco = instituicaoFinanceiraRiscoRepository.findOne(
      instituicaoFinanceiraRiscoRepository.instituicaoFinanceira(new InstituicaoFinanceira(instituicao))
        .and(instituicaoFinanceiraRiscoRepository.categoria(input.getCategoria()))
    );
    if (risco.isPresent() && !risco.get().getId().equals(input.getId())) {
      throw new NegocioException(
        "Risco já cadastrado", String.format("Já existe a categoria %s cadastrada na isntituição", input.getCategoria()), null, input
      );
    }
    if (!InstituicaoFinanceiraRiscoCategoriaOpcao.findByCategoria(input.getCategoria()).contains(input.getOpcao())) {
      throw new NegocioException(
        "Risco inválido", String.format("Opção %s é inválida para a categoria %s.", input.getOpcao(), input.getCategoria()), null, input
      );
    }
  }

}
