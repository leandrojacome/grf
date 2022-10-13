package br.com.poupex.investimento.recursosfinanceiros.infrastructure.adapter;


import br.com.poupex.investimento.recursosfinanceiros.domain.enums.InstituicaoFinanceiraTipo;
import br.com.poupex.investimento.recursosfinanceiros.domain.exception.NegocioException;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ContatoInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.EnderecoInputOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.InstituicaoFinanceiraOutputDetalhe;
import br.com.poupex.investimento.recursosfinanceiros.infrastructure.util.ExecutionUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CnpjExternoAdapter {

  private final ObjectMapper mapper;

  public InstituicaoFinanceiraOutputDetalhe execute(final Object source) {
    val retorno = new InstituicaoFinanceiraOutputDetalhe();
    try {
      val json = mapper.readValue(source.toString(), new TypeReference<HashMap<String, String>>() {
      });
      if (json.containsKey("error")) {
        throw new NegocioException("Busca CNPJ", json.get("error"));
      }
      retorno.setNome(json.get("RAZAO SOCIAL"));
      retorno.setAbreviacao(json.get("NOME FANTASIA"));
      retorno.setAbreviacao(json.get("NOME FANTASIA"));
      retorno.setCnpj(json.get("CNPJ"));
      retorno.setMatriz(Boolean.FALSE);
      retorno.setMatriz(Boolean.FALSE);
      retorno.setTipo(ExecutionUtil.getSafe(() -> InstituicaoFinanceiraTipo.valueOf(json.get("CNAE PRINCIPAL DESCRICAO"))));
      val endereco = new EnderecoInputOutput();
      endereco.setCep(json.get("CEP"));
      endereco.setLogradouro(json.get("LOGRADOURO").concat(json.get("BAIRRO")));
      endereco.setNumero(json.get("NUMERO"));
      endereco.setComplemento(json.get("COMPLEMENTO"));
      endereco.setCidade(json.get("CIDADE"));
      endereco.setUf(json.get("UF"));
      retorno.setEndereco(endereco);
      val contato = new ContatoInputOutput();
      contato.setTelefone1(json.getOrDefault("DDD", "00").concat(json.get("TELEFONE")));
      contato.setEmail(json.get("EMAIL"));
      retorno.setContatos(List.of(contato));
    } catch (JsonProcessingException e) {
      log.error("Erro na busca de Cnpj externo", e);
      throw new NegocioException("Busca CNPJ", e.getMessage());
    }
    return retorno;
  }

}
