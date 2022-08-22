package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.model.InstituicaoFinanceiraInput;
import br.com.poupex.investimento.recursosfinanceiros.model.ValidacaoModel;
import br.com.poupex.investimento.recursosfinanceiros.entity.InstituicaoFinanceira;
import br.com.poupex.investimento.recursosfinanceiros.exception.NegocioException;
import br.com.poupex.investimento.recursosfinanceiros.repository.InstituicaoFinanceiraRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidaInstituicaoFinanceiraMatrizGrupoService {

  private final InstituicaoFinanceiraRepository instituicaoFinanceiraRepository;

  public void execute(final InstituicaoFinanceiraInput input) {
    if (!input.getMatriz() && Strings.isBlank(input.getGrupo())) {
      throw new NegocioException(
        "Selecione um grupo",
        "A instituição que não é matriz deve fazer parte de um grupo.",
        List.of(new ValidacaoModel("grupo", "grupo é obrigatório")),
        input
      );
    }
    if (Strings.isNotBlank(input.getGrupo()) && !instituicaoFinanceiraRepository.existsById(input.getGrupo())) {
      throw new NegocioException(
        "Grupo inválido",
        "O grupo selecionado não existe.",
        List.of(new ValidacaoModel("grupo", "grupo é inválido")),
        input
      );
    }
  }

  public void execute(final String id, final InstituicaoFinanceiraInput input) {
    execute(input);
    if (Strings.isNotBlank(id) && id.equals(input.getGrupo())) {
      throw new NegocioException(
        "Grupo inválido",
        "Não é possível definir a própria instituição como matriz dela mesma.",
        List.of(new ValidacaoModel("grupo", "grupo é inválido")),
        input
      );
    }
    if (Strings.isNotBlank(id) && !input.getMatriz() &&
      instituicaoFinanceiraRepository.existsByGrupo(new InstituicaoFinanceira(id))) {
      throw new NegocioException(
        "A instituição é matriz",
        "A instituição é matriz de um grupo que tem outras instituições associadas.",
        List.of(new ValidacaoModel("matriz", "matriz é inválido")),
        input
      );
    }

  }

}
