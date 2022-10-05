package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.entity.data.InstituicaoFinanceiraRisco;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.RiscoInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.exception.RecursoNaoEncontradoException;
import br.com.poupex.investimento.recursosfinanceiros.repository.InstituicaoFinanceiraRiscoRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EditarInstituicaoFinanceiraRiscoService {

  private final ModelMapper mapper;
  private final ValidaInstituicaoFinanceiraRiscosOpcoesService validaInstituicaoFinanceiraRiscosOpcoesService;
  private final ObterInstituicaoFinanceiraRiscoService obterInstituicaoFinanceiraRiscoService;
  private final InstituicaoFinanceiraRiscoRepository instituicaoFinanceiraRiscoRepository;
  @Lazy
  private final CadastrarInstituicaoFinanceiraRiscoService cadastrarInstituicaoFinanceiraRiscoService;

  public ResponseModel execute(final String id, final RiscoInputOutput input) {
    try {
      validaInstituicaoFinanceiraRiscosOpcoesService.execute(input);
      val riscos = obterInstituicaoFinanceiraRiscoService.id(id);
      BeanUtils.copyProperties(
        mapper.map(input, InstituicaoFinanceiraRisco.class), riscos,
        "id", "instituicaoFinanceira", "cadastro", "atualizacao"
      );
      return new ResponseModel(
        LocalDateTime.now(),
        HttpStatus.OK.value(),
        "Alteração realizada com sucesso",
        String.format("Os Riscos da Instituição %s foram alterados com sucesso", id),
        "Riscos da Instituição alterados com sucesso",
        null,
        mapper.map(instituicaoFinanceiraRiscoRepository.save(riscos), RiscoInputOutput.class
        )
      );
    } catch (final RecursoNaoEncontradoException e) {
      return cadastrarInstituicaoFinanceiraRiscoService.execute(id, input);
    }
  }

}
