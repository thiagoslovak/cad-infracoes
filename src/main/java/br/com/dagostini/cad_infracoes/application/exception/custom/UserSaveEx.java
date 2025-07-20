package br.com.dagostini.cad_infracoes.application.exception.custom;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
public class UserSaveEx extends  RuntimeException {

    private String message;
}