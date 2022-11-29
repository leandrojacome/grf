package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.IndicadorFinanceiro;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ChaveLabelDescricaoOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.IndicadorFinanceiroRepository;
import java.time.LocalDateTime;
import java.util.Comparator;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PesquisarIndicadoresFinanceirosService {

  private final IndicadorFinanceiroRepository indicadorFinanceiroRepository;

  public ResponseModel execute(final String sigla, final String nome) {
    val resultado = indicadorFinanceiroRepository.findAll(spec(sigla, nome));
    val mensagem = resultado.size() == 0 ? "Nenhum registro encontrado" : null;
    return new ResponseModel(LocalDateTime.now(), HttpStatus.OK.value(), "Indicadores Financeiros", null, mensagem, null,
      resultado.stream().map(indicador -> new ChaveLabelDescricaoOutput(indicador.getId(), indicador.getSigla(), indicador.getNome()))
        .sorted(Comparator.comparing(ChaveLabelDescricaoOutput::label)).toList()
    );
  }

  public Specification<IndicadorFinanceiro> spec(final String sigla, final String nome) {
    return indicadorFinanceiroRepository.sigla(sigla).and(indicadorFinanceiroRepository.nome(nome));
  }

}
