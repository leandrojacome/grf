package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.exception.NegocioException;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ValidacaoModel;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidaEnderecoCepService {

  private final RecuperarCepExternoService recuperarCepExternoService;

  public void execute(final String cep) {
    try {
      recuperarCepExternoService.execute(cep);
    } catch (final RecursoNaoEncontradoException e) {
      throw new NegocioException(
        "Cep inválido",
        "O cep informado é inválido ou não foi encontrado.",
        List.of(new ValidacaoModel("cep", "cep é inválido")),
        cep
      );
    }
  }


}
