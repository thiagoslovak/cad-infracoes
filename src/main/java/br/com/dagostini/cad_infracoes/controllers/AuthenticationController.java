package br.com.dagostini.cad_infracoes.controllers;

import br.com.dagostini.cad_infracoes.core.LoginResponse;
import br.com.dagostini.cad_infracoes.application.UserUseCaseImpl;
import br.com.dagostini.cad_infracoes.application.exception.custom.UserConsultEx;
import br.com.dagostini.cad_infracoes.application.exception.custom.UserSaveEx;
import br.com.dagostini.cad_infracoes.core.AuthenticationRequest;
import br.com.dagostini.cad_infracoes.core.RegisterRequest;
import br.com.dagostini.cad_infracoes.core.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserUseCaseImpl userUseCase;

    @PostMapping("/login")
    @Operation(summary = "Logging users.", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Success in processing the request."),
            @ApiResponse(responseCode = "401", description = "Unauthorized access."),
            @ApiResponse(responseCode = "404", description = "Requested resource not found."),
            @ApiResponse(responseCode = "500", description = "Unexpected system error."),
    })
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid AuthenticationRequest data) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body((new LoginResponse(userUseCase.getTokenByAuthenticationRequest(data))));
    }

    @PostMapping("/register")
    @Operation(summary = "Save User.", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Success in processing the request."),
            @ApiResponse(responseCode = "401", description = "Unauthorized access."),
            @ApiResponse(responseCode = "404", description = "Requested resource not found."),
            @ApiResponse(responseCode = "500", description = "Unexpected system error."),
    })
    public ResponseEntity<UserResponse> register(@RequestBody @Valid RegisterRequest data) {
        try {
            userUseCase.validateUserDetailsByLogin(data.getLogin());
            return ResponseEntity.status(HttpStatus.CREATED).body(userUseCase.saveUser(data));
        } catch (UserConsultEx ex) {
            throw new UserConsultEx("Error retrieving users.");
        } catch (UserSaveEx ex) {
            throw new UserSaveEx("Error saving user.");
        }
    }
}