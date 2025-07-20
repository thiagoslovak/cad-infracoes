package br.com.dagostini.cad_infracoes.controllers;

import br.com.dagostini.cad_infracoes.application.ViolationUseCaseImpl;
import br.com.dagostini.cad_infracoes.application.exception.custom.EquipmentConsultEx;
import br.com.dagostini.cad_infracoes.application.exception.custom.ViolationSaveEx;
import br.com.dagostini.cad_infracoes.core.ViolationRequest;
import br.com.dagostini.cad_infracoes.core.ViolationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/violations")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class ViolationController {

    private final ViolationUseCaseImpl violationUseCase;

    @PostMapping()
    @Operation(summary = "Save Violation.", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Success in processing the request."),
            @ApiResponse(responseCode = "401", description = "Unauthorized access."),
            @ApiResponse(responseCode = "404", description = "Requested resource not found."),
            @ApiResponse(responseCode = "500", description = "Unexpected system error."),
    })
    public ResponseEntity<ViolationResponse> saveViolation(@RequestBody ViolationRequest violationRequest) {
        try {
            ViolationResponse violationResponse = violationUseCase.saveViolation(violationRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(violationResponse);
        } catch (ViolationSaveEx ex) {
            throw new ViolationSaveEx("Error saving violation.");
        } catch (EquipmentConsultEx ex) {
            throw new EquipmentConsultEx("Equipment is not active.");
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consult Violation by id.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success in processing the request."),
            @ApiResponse(responseCode = "401", description = "Unauthorized access."),
            @ApiResponse(responseCode = "404", description = "Requested resource not found."),
            @ApiResponse(responseCode = "500", description = "Unexpected system error."),
    })
    public ResponseEntity<ViolationResponse> consultViolationById(@PathVariable(value = "id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(violationUseCase.getViolationById(id));
    }
}