package br.com.poupex.investimento.recursosfinanceiros.api.controller;


import br.com.poupex.investimento.recursosfinanceiros.model.ResponseModel;
import br.com.poupex.investimento.recursosfinanceiros.service.RecuperarCepExternoService;
import br.com.poupex.investimento.recursosfinanceiros.service.RecuperarCnpjExternoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("uteis")
@RequiredArgsConstructor
public class UtilController {

  private final RecuperarCepExternoService recuperarCepExternoService;
  private final RecuperarCnpjExternoService recuperarCnpjExternoService;

  @GetMapping("cep/{cep}")
  public ResponseEntity<ResponseModel> cep(@PathVariable String cep) {
    return ResponseEntity.ok(recuperarCepExternoService.execute(cep));
  }

  @GetMapping("cnpj/{cnpj}")
  public ResponseEntity<ResponseModel> cnpj(@PathVariable String cnpj) {
    return ResponseEntity.ok(recuperarCnpjExternoService.execute(cnpj));
  }

}
