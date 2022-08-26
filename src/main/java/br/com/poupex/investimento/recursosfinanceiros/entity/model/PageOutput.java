package br.com.poupex.investimento.recursosfinanceiros.entity.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PageOutput {
  private List<?> content;
  private Integer pageIndex;
  private Integer pageSize;
  private Long totalElements;
  private List<String> sort;
}
