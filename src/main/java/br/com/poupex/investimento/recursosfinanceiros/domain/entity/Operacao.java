package br.com.poupex.investimento.recursosfinanceiros.domain.entity;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.*;
import br.com.poupex.investimento.recursosfinanceiros.service.CadastrarOperacaoRendaFixaCompromissadaService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

  @Override
  public void prePersist() {
    try {
      val count = CadastrarOperacaoRendaFixaCompromissadaService.singleton.count(
        CadastrarOperacaoRendaFixaCompromissadaService.singleton.cadastro(LocalDate.now())
      ) + 1;
      setBoleta(String.format("%s%04d", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")), count));
    } catch (final NullPointerException ignored) {
    }
  }

}
