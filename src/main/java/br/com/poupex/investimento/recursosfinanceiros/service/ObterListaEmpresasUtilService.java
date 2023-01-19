package br.com.poupex.investimento.recursosfinanceiros.service;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.poupex.investimento.recursosfinanceiros.domain.enums.Empresa;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.EmpresaOutput;
import br.com.poupex.investimento.recursosfinanceiros.domain.model.ResponseModel;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
public class ObterListaEmpresasUtilService {

	private final ModelMapper mapper;

	public ResponseModel execute() {
		val list = Arrays.stream(Empresa.values()).map(r -> mapper.map(r, EmpresaOutput.class)).toList();
		return new ResponseModel(list);
	}

}
