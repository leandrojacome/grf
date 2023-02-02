package br.com.poupex.investimento.recursosfinanceiros.domain.entity;

import br.com.poupex.investimento.recursosfinanceiros.service.CadastrarOperacaoService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.val;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "OPERACAO", schema = "GESTAO_RECURSOS_FINANCEIROS")
public class Operacao extends AbstractEntidadeBase {

  @Column(name = "NUMERO_BOLETA", nullable = false)
  private String boleta;

  @Column(name = "TIPO", nullable = false)
  private String tipo;

  @Override
  public void prePersist() {
    try {
      val count = CadastrarOperacaoService.singleton.count(
        CadastrarOperacaoService.singleton.cadastro(LocalDate.now())
      ) + 1;
      setBoleta(String.format("%s%04d", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")), count));
      setTipo(this.getClass().getSimpleName());
    } catch (final NullPointerException ignored) {
    }
  }

}
