package br.com.dagostini.cad_infracoes.core;

import br.com.dagostini.cad_infracoes.core.entities.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private UUID id;
    private String login;
    private String password;
    private UserRole role;
}