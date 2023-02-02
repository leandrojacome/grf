package br.com.poupex.investimento.recursosfinanceiros.service;

import java.util.Arrays;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.PeriodoCupom;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.PeriodoCupomOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
public class obterListaPeriodoCupomUtilService {

	private final ModelMapper mapper;

	public ResponseModel execute() {
		Converter<PeriodoCupom, PeriodoCupomOutput> periodoCupomConvert = new Converter<PeriodoCupom, PeriodoCupomOutput>() {
			public PeriodoCupomOutput convert(MappingContext<PeriodoCupom, PeriodoCupomOutput> context) {
				var source = context.getSource();
				var destination = new PeriodoCupomOutput();
				destination.setDescricao(source.getDescricao());
				destination.setSigla(source.toString());
				return destination;
			}
		};
		mapper.addConverter(periodoCupomConvert);
		val list = Arrays.stream(PeriodoCupom.values()).map(r -> mapper.map(r, PeriodoCupomOutput.class)).toList();
		return new ResponseModel(list);
	}

}
