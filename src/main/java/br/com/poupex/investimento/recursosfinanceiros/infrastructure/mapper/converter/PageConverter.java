package br.com.poupex.investimento.recursosfinanceiros.infrastructure.mapper.converter;

import br.com.poupex.investimento.recursosfinanceiros.domain.model.PageOutput;
import java.util.stream.Collectors;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class PageConverter {

  public PageConverter(final ModelMapper mapper) {
    mapper.createTypeMap(PageImpl.class, PageOutput.class).setConverter(context -> {
      val page = context.getSource();
      return new PageOutput(
        page.getContent(),
        page.getNumber(),
        page.getSize(),
        page.getTotalElements(),
        page.getSort().get().map(Sort.Order::toString).collect(Collectors.toList())
      );
    });
  }

}
