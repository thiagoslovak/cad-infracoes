package br.com.dagostini.cad_infracoes.application.exception.handler;

import br.com.dagostini.cad_infracoes.application.exception.custom.EquipmentConsultEx;
import br.com.dagostini.cad_infracoes.application.exception.custom.EquipmentSaveEx;
import br.com.dagostini.cad_infracoes.application.exception.custom.UserConsultEx;
import br.com.dagostini.cad_infracoes.application.exception.custom.UserSaveEx;
import br.com.dagostini.cad_infracoes.application.exception.custom.ViolationConsultEx;
import br.com.dagostini.cad_infracoes.application.exception.custom.ViolationSaveEx;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler  {

    @ExceptionHandler(EquipmentSaveEx.class)
    public ResponseEntity<Object> handleEquipmentSaveEx(EquipmentSaveEx ex, WebRequest  request) {

        var handlerResponse = HandlerResponse.builder().
                errorCode(HttpStatus.INTERNAL_SERVER_ERROR).
                exceptionMessage(ex.getMessage()).
                build();

        return new ResponseEntity<>(handlerResponse, new HttpHeaders(), handlerResponse.getErrorCode());
    }

    @ExceptionHandler(EquipmentConsultEx.class)
    public ResponseEntity<Object> handleEquipmentConsultEx(EquipmentConsultEx ex, WebRequest request) {

        var handlerResponse = HandlerResponse.builder().
                errorCode(HttpStatus.NOT_FOUND).
                exceptionMessage(ex.getMessage()).
                build();

        return new ResponseEntity<>(handlerResponse, new HttpHeaders(), handlerResponse.getErrorCode());
    }

    @ExceptionHandler(UserSaveEx.class)
    public ResponseEntity<Object> handleUserSaveEx(UserSaveEx ex, WebRequest  request) {

        var handlerResponse = HandlerResponse.builder().
                errorCode(HttpStatus.INTERNAL_SERVER_ERROR).
                exceptionMessage(ex.getMessage()).
                build();

        return new ResponseEntity<>(handlerResponse, new HttpHeaders(), handlerResponse.getErrorCode());
    }

    @ExceptionHandler(UserConsultEx.class)
    public ResponseEntity<Object> handleUserConsultEx(UserConsultEx ex, WebRequest request) {

        var handlerResponse = HandlerResponse.builder().
                errorCode(HttpStatus.NOT_FOUND).
                exceptionMessage(ex.getMessage()).
                build();

        return new ResponseEntity<>(handlerResponse, new HttpHeaders(), handlerResponse.getErrorCode());
    }

    @ExceptionHandler(ViolationSaveEx.class)
    public ResponseEntity<Object> handleViolationSaveEx(ViolationSaveEx ex, WebRequest  request) {

        var handlerResponse = HandlerResponse.builder().
                errorCode(HttpStatus.INTERNAL_SERVER_ERROR).
                exceptionMessage(ex.getMessage()).
                build();

        return new ResponseEntity<>(handlerResponse, new HttpHeaders(), handlerResponse.getErrorCode());
    }

    @ExceptionHandler(ViolationConsultEx.class)
    public ResponseEntity<Object> handleViolationConsultEx(ViolationConsultEx ex, WebRequest request) {

        var handlerResponse = HandlerResponse.builder().
                errorCode(HttpStatus.NOT_FOUND).
                exceptionMessage(ex.getMessage()).
                build();

        return new ResponseEntity<>(handlerResponse, new HttpHeaders(), handlerResponse.getErrorCode());
    }
}