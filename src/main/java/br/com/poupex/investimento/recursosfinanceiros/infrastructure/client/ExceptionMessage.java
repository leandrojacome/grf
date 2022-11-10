package br.com.poupex.investimento.recursosfinanceiros.infrastructure.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown=true)
@Data
public class ExceptionMessage {
	
	private int code;
	private String description;
	private String fields;
	

    @Override
    public String toString() {
        return "ExceptionMessage [code=" + code + ", description=" + description + ", fields=" + fields + "]";
    }

}
