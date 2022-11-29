package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.model.IndicadorFinanceiroOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.PageOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.IndicadorFinanceiroRepository;
import java.time.LocalDate;
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
public class PesquisarIndicadoresFinanceirosPagedService {

  private final IndicadorFinanceiroRepository indicadorFinanceiroRepository;
  private final PesquisarIndicadoresFinanceirosService pesquisarIndicadoresFinanceirosService;
  private final ModelMapper mapper;

  public ResponseModel execute(final String sigla, final String nome, final Pageable pageable) {
    val resultado = indicadorFinanceiroRepository.findAll(pesquisarIndicadoresFinanceirosService.spec(sigla, nome), pageable);
    val mensagem = resultado.getTotalElements() == 0 ? "Nenhum registro encontrado" : null;
    val page = new PageImpl<>(
      resultado.getContent().stream().map(r -> mapper.map(r, IndicadorFinanceiroOutput.class)).collect(Collectors.toList()),
      pageable,
      resultado.getTotalElements()
    );
    return new ResponseModel(LocalDateTime.now(), HttpStatus.OK.value(), null, null, mensagem, null, mapper.map(page, PageOutput.class));
  }

}
