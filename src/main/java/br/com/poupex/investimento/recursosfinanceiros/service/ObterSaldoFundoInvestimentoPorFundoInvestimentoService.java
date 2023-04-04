package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.SaldoFundoInvestimento;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.SaldoFundosInvestimentosOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.SaldoFundoInvestimentoRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ObterSaldoFundoInvestimentoPorFundoInvestimentoService {

  private final ObterFundoInvestimentoService obterFundoInvestimentoService;
  private final SaldoFundoInvestimentoRepository saldoFundoInvestimentoRepository;
  private final ModelMapper mapper;

  public SaldoFundoInvestimento fundo(final String fundosInvestimentos) {
    val fundoInvestimento = obterFundoInvestimentoService.getFundoInvestimento(fundosInvestimentos);
    return saldoFundoInvestimentoRepository.findOne(saldoFundoInvestimentoRepository.fundoInvestimento(fundoInvestimento))
      .orElseGet(() -> {
        val saldo = new SaldoFundoInvestimento();
        saldo.setFundoInvestimento(fundoInvestimento);
        saldo.setSaldoFinanceiro(BigDecimal.ZERO);
        saldo.setQuantidadeCota(BigDecimal.ZERO);
        saldo.setCadastro(LocalDateTime.now());
        return saldoFundoInvestimentoRepository.save(saldo);
      });
  }

  public ResponseModel execute(final String fundosInvestimentos) {
    return new ResponseModel(mapper.map(fundo(fundosInvestimentos), SaldoFundosInvestimentosOutput.class));
  }

}
