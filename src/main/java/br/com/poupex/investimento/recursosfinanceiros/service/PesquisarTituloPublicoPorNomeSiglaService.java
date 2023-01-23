package br.com.poupex.investimento.recursosfinanceiros.service;

import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.TituloPublicoInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.client.GestaoInstrumentosFinanceirosApiClient;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.TituloPublicoRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PesquisarTituloPublicoPorNomeSiglaService {

  private final TituloPublicoRepository tituloPublicoRepository;
  private final GestaoInstrumentosFinanceirosApiClient gestaoInstrumentosFinanceirosApiClient;
  private final ModelMapper mapper;

  public ResponseModel execute(final String sigla) {
    val titulos = tituloPublicoRepository.findAll(tituloPublicoRepository.sigla(sigla)).stream().map(titulo -> {
      val output = mapper.map(titulo, TituloPublicoInputOutput.class);
      val tituloGif = gestaoInstrumentosFinanceirosApiClient.getInstrumentoFinanceiro(titulo.getCodigoGif());
      BeanUtils.copyProperties(tituloGif, output);
      return output;
    }).toList();
    return new ResponseModel(titulos.isEmpty() ? "Nenhum título encontrado" : null, titulos);
  }

}
