package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstituicaoFinanceira;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.InstituicaoFinanceiraRiscoAgenciaModalidade;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.InstituicaoFinanceiraRiscoClassificacao;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.NegocioException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.RiscoInputRisco;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ValidacaoModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.InstituicaoFinanceiraRiscoRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidaInstituicaoFinanceiraRiscoClassificacaoService {

  private final InstituicaoFinanceiraRiscoRepository instituicaoFinanceiraRiscoRepository;

  public void execute(final String instituicao, final RiscoInputRisco input) {
    val risco = instituicaoFinanceiraRiscoRepository.findOne(
      instituicaoFinanceiraRiscoRepository.instituicaoFinanceira(new InstituicaoFinanceira(instituicao))
        .and(instituicaoFinanceiraRiscoRepository.agenciaModalidade(input.getAgenciaModalidade()))
    );
    if (risco.isPresent()) {
      throw new NegocioException(
        "Risco já cadastrado",
        String.format("Já existe a agência/modalidade %s cadastrada nessa instituição", input.getAgenciaModalidade().getLabel()),
        null,
        input
      );
    }
    execute(input.getAgenciaModalidade(), input.getClassificacao());
    execute(input.getAgenciaModalidade(), input.getResumo());
  }

  public void execute(final InstituicaoFinanceiraRiscoAgenciaModalidade agenciaModalidade, final InstituicaoFinanceiraRiscoClassificacao classificacao) {
    if (!InstituicaoFinanceiraRiscoClassificacao.findByAgenciaModalidade(agenciaModalidade).contains(classificacao)) {
      throw new NegocioException(
        "Risco inválido",
        String.format("A Classificacao %s é inválida para a agência/modalidade %s.", classificacao, agenciaModalidade),
        null,
        RiscoInputRisco.builder().agenciaModalidade(agenciaModalidade).classificacao(classificacao).build()
      );
    }
  }

  public void execute(final InstituicaoFinanceiraRiscoAgenciaModalidade agenciaModalidade, final String resumo) {
    try {
      if (!resumo.isEmpty() && !InstituicaoFinanceiraRiscoAgenciaModalidade.CLASSIFICACAO.equals(agenciaModalidade)) {
        throw new NegocioException(
          "Campo inválido",
          String.format(
            "O resumo so pode ser salvo na modalidade: %s", InstituicaoFinanceiraRiscoAgenciaModalidade.CLASSIFICACAO.getLabel()
          ),
          List.of(new ValidacaoModel("resumo", "resumo indevido")),
          String.format("agencia: %s, resumo: %s", agenciaModalidade, resumo)
        );
      }
      if (resumo.length() < 10 || resumo.length() > 3000) {
        throw new NegocioException(
          "Validação",
          "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.",
          List.of(new ValidacaoModel("resumo", "tamanho deve ser entre 10 e 3000")),
          String.format("agencia: %s, resumo: %s", agenciaModalidade, resumo)
        );
      }
    } catch (final NullPointerException ignored) {
    }
  }

}
