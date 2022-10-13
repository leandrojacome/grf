package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.entity.InstituicaoFinanceira;
import br.com.poupex.investimento.recursosfinanceiros.domain.enums.InstituicaoFinanceiraTipo;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ChaveLabelDescricaoOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.InstituicaoFinanceiraRepository;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.util.ExecutionUtil;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PesquisarInstituicoesFinanceirasService {

  private final InstituicaoFinanceiraRepository instituicaoFinanceiraRepository;

  public ResponseModel execute(final String nome, final String cnpj, final InstituicaoFinanceiraTipo tipo, final String grupo) {
    val resultado = instituicaoFinanceiraRepository.findAll(spec(nome, cnpj, tipo, grupo));
    val mensagem = resultado.size() == 0 ? "Nenhum registro encontrado" : null;
    return new ResponseModel(LocalDateTime.now(), HttpStatus.OK.value(), "Instituições Financeiras", null, mensagem, null,
      resultado.stream().map(instituicao -> new ChaveLabelDescricaoOutput(instituicao.getId(), instituicao.getAbreviacao(), instituicao.getNome()))
        .sorted(Comparator.comparing(ChaveLabelDescricaoOutput::label)).toList()
    );
  }

  public Specification<InstituicaoFinanceira> spec(String nome, String cnpj, InstituicaoFinanceiraTipo tipo, String grupo) {
    return ExecutionUtil.and(
      instituicaoFinanceiraRepository.init(),
      new ArrayList<>() {{
        add(instituicaoFinanceiraRepository.nome(nome));
        add(instituicaoFinanceiraRepository.cnpj(cnpj));
        add(instituicaoFinanceiraRepository.tipo(tipo));
        add(instituicaoFinanceiraRepository.grupo(grupo));
      }}
    );
  }

}
