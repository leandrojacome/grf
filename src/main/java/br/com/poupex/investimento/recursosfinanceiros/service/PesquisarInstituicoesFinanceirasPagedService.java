package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.entity.model.InstituicaoFinanceiraOutput;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.PageOutput;
import br.com.poupex.investimento.recursosfinanceiros.entity.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.enums.InstituicaoFinanceiraTipo;
import br.com.poupex.investimento.recursosfinanceiros.repository.InstituicaoFinanceiraRepository;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PesquisarInstituicoesFinanceirasPagedService {

  private final PesquisarInstituicoesFinanceirasService pesquisarInstituicoesFinanceirasService;
  private final InstituicaoFinanceiraRepository instituicaoFinanceiraRepository;
  private final ModelMapper mapper;

  public ResponseModel execute(
    final String nome, final String cnpj, final InstituicaoFinanceiraTipo tipo, final String grupo, final Pageable pageable
  ) {
    val resultado = instituicaoFinanceiraRepository.findAll(pesquisarInstituicoesFinanceirasService.spec(nome, cnpj, tipo, grupo), pageable);
    val mensagem = resultado.getTotalElements() == 0 ? "Nenhum registro encontrado" : null;
    val page = new PageImpl<>(
      resultado.getContent().stream().map(r -> mapper.map(r, InstituicaoFinanceiraOutput.class)).collect(Collectors.toList()),
      pageable,
      resultado.getTotalElements()
    );
    return new ResponseModel(LocalDateTime.now(), HttpStatus.OK.value(), null, null, mensagem, null, mapper.map(page, PageOutput.class));
  }

}
