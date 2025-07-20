package br.com.dagostini.cad_infracoes.application.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
public class HandlerResponse {

    private HttpStatus errorCode;
    private String exceptionMessage;
}