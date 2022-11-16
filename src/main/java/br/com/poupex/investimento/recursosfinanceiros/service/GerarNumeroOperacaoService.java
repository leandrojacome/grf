package br.com.poupex.investimento.recursosfinanceiros.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class GerarNumeroOperacaoService {

    private final SimpleDateFormat dataFormat = new SimpleDateFormat("yyyyMMdd");

    // Solução temporário para atender o MVP da integração do GRF e GIF
    public Long generateValue() {
        var now = new Date();
                return Long.valueOf(dataFormat.format(now) + new Random().nextInt(1000));
    }
}
