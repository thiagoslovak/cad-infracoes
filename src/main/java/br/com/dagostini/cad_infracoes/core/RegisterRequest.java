package br.com.dagostini.cad_infracoes.core;

import br.com.dagostini.cad_infracoes.core.entities.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String login;
    private String password;
    private UserRole role;
}