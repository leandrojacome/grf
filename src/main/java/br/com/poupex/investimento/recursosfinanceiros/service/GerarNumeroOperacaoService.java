package br.com.poupex.investimento.recursosfinanceiros.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.poupex.investimento.recursosfinanceiros.infrastructure.repository.OperacaoRendaFixaDefinitivaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GerarNumeroOperacaoService {

	private final OperacaoRendaFixaDefinitivaRepository operacaoFinanceiraRepository;
    private final SimpleDateFormat dataFormat = new SimpleDateFormat("yyyyMMdd");

    // Solução temporário para atender o MVP da integração do GRF e GIF
    public Long generateValue() {
        var now = new Date();
        return Long.valueOf(dataFormat.format(now) + String.format("%04d", operacaoFinanceiraRepository.getNextNumeroOperacaoSeq()));
    }
}
