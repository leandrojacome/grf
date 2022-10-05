package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.entity.data.InstituicaoFinanceira;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.ContatoInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.InstituicaoFinanceiraInputEditar;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.InstituicaoFinanceiraOutput;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.repository.InstituicaoFinanceiraRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EditarInstituicaoFinanceiraService {

  private final ModelMapper mapper;
  private final ValidaInstituicaoFinanceiraMatrizGrupoService validaInstituicaoFinanceiraMatrizGrupoService;
  private final ObterInstituicaoFinanceiraService obterInstituicaoFinanceiraService;
  private final InstituicaoFinanceiraRepository instituicaoFinanceiraRepository;
  private final EditarInstituicaoFinanceiraEnderecoService editarInstituicaoFinanceiraEnderecoService;
  private final ObterInstituicaoFinanceiraContatosService obterInstituicaoFinanceiraContatosService;
  private final ExcluirInstituicaoFinanceiraContatoService excluirInstituicaoFinanceiraContatoService;
  private final CadastrarInstituicaoFinanceiraContatoService cadastrarInstituicaoFinanceiraContatoService;
  private final EditarInstituicaoFinanceiraContabilService editarInstituicaoFinanceiraContabilService;
  private final EditarInstituicaoFinanceiraRiscoService editarInstituicaoFinanceiraRiscoService;

  @Transactional
  public ResponseModel execute(final String id, final InstituicaoFinanceiraInputEditar input) {
    validaInstituicaoFinanceiraMatrizGrupoService.execute(id, input);
    val instituicao = obterInstituicaoFinanceiraService.id(id);
    BeanUtils.copyProperties(
      mapper.map(input, InstituicaoFinanceira.class), instituicao,
      "id", "cnpj", "cadastro", "atualizacao"
    );
    if (Objects.nonNull(input.getEndereco())) {
      editarInstituicaoFinanceiraEnderecoService.execute(id, input.getEndereco());
    }
    val idsContatosEnviados = input.getContatos() != null ?
      input.getContatos().stream().map(ContatoInputOutput::getId).filter(Objects::nonNull).toList() :
      new ArrayList<String>();
    obterInstituicaoFinanceiraContatosService.lista(id).stream().filter(contato -> !idsContatosEnviados.contains(contato.getId())).forEach(
      contato -> excluirInstituicaoFinanceiraContatoService.execute(id, contato.getId())
    );
    if (Objects.nonNull(input.getContatos())) {
      input.getContatos().forEach(contato -> {
        if (Objects.isNull(contato.getId())) {
          cadastrarInstituicaoFinanceiraContatoService.execute(id, contato);
        }
      });
    }
    if (Objects.nonNull(input.getContabil())) {
      editarInstituicaoFinanceiraContabilService.execute(id, input.getContabil());
    }
    if (Objects.nonNull(input.getRisco())) {
      editarInstituicaoFinanceiraRiscoService.execute(id, input.getRisco());
    }
    return new ResponseModel(
      LocalDateTime.now(),
      HttpStatus.OK.value(),
      "Alteração realizada com sucesso",
      String.format("A instituição %s foi alterada com sucesso", input.getNome()),
      "Instituição alterada com sucesso",
      null,
      mapper.map(instituicaoFinanceiraRepository.save(instituicao), InstituicaoFinanceiraOutput.class
      )
    );
  }

}
