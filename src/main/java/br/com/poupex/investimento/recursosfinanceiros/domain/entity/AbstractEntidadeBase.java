package br.com.poupex.investimento.recursosfinanceiros.domain.entity;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
@Slf4j
public abstract class AbstractEntidadeBase {

  public AbstractEntidadeBase(final String id) {
    this.id = id;
  }

  @Id
  @Column(name = "ID", nullable = false, length = 36)
  private String id;

  @CreationTimestamp
  @Column(name = "CADASTRO", nullable = false)
  private LocalDateTime cadastro;

  @UpdateTimestamp
  @Column(name = "ATUALIZACAO", nullable = false)
  private LocalDateTime atualizacao;

  @PrePersist
  public void abstractPrePersist() {
    if (id == null) {
      setId(UUID.randomUUID().toString());
    }
    prePersist();
  }

  @PreUpdate
  public void abstractPreUpdate() {
    preUpdate();
  }

  public void prePersist() {
    log.debug("Usando prePersist default da entidade base");
  }

  public void preUpdate() {
    log.debug("Usando preUpdate default da entidade base");
  }

}
