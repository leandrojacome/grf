package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.entity.model.InstituicaoFinanceiraInput;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.ValidacaoModel;
import br.com.poupex.investimento.recursosfinanceiros.entity.data.InstituicaoFinanceira;
import br.com.poupex.investimento.recursosfinanceiros.exception.NegocioException;
import br.com.poupex.investimento.recursosfinanceiros.repository.InstituicaoFinanceiraRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidaInstituicaoFinanceiraMatrizGrupoService {

  private final InstituicaoFinanceiraRepository instituicaoFinanceiraRepository;

  public void execute(final InstituicaoFinanceiraInput input) {
    if (!input.getMatriz() && input.getGrupo() == null) {
      throw new NegocioException(
        "Selecione um grupo",
        "A instituição que não é matriz deve fazer parte de um grupo.",
        List.of(new ValidacaoModel("grupo", "grupo é obrigatório")),
        input
      );
    }
    if (input.getGrupo() != null && !instituicaoFinanceiraRepository.existsById(input.getGrupo())) {
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
    if (id != null && id.equals(input.getGrupo())) {
      throw new NegocioException(
        "Grupo inválido",
        "Não é possível definir a própria instituição como matriz dela mesma.",
        List.of(new ValidacaoModel("grupo", "grupo é inválido")),
        input
      );
    }
    if (id != null && !input.getMatriz() && instituicaoFinanceiraRepository.existsByGrupo(new InstituicaoFinanceira(id))) {
      throw new NegocioException(
        "A Instituição é uma matriz",
        "A Instituição é uma matriz e tem outras instituições associadas.",
        List.of(new ValidacaoModel("matriz", "matriz é inválido")),
        input
      );
    }
  }

}
