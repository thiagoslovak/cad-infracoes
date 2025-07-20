package br.com.dagostini.cad_infracoes.controllers;
import br.com.dagostini.cad_infracoes.application.EquipmentUseCaseImpl;
import br.com.dagostini.cad_infracoes.application.exception.custom.EquipmentConsultEx;
import br.com.dagostini.cad_infracoes.application.exception.custom.EquipmentSaveEx;
import br.com.dagostini.cad_infracoes.core.EquipmentRequest;
import br.com.dagostini.cad_infracoes.core.EquipmentResponse;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/equipments")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class EquipmentController {

    private final EquipmentUseCaseImpl service;

    @PostMapping()
    @Operation(summary = "Save Equipment.", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Success in processing the request."),
            @ApiResponse(responseCode = "401", description = "Unauthorized access."),
            @ApiResponse(responseCode = "404", description = "Requested resource not found."),
            @ApiResponse(responseCode = "500", description = "Unexpected system error."),

    })
    public ResponseEntity<EquipmentResponse> saveEquipment(@RequestBody EquipmentRequest equipmentRequest) {
        try {
            EquipmentResponse equipmentResponse = service.saveEquipment(equipmentRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(equipmentResponse);
        } catch (Exception ex) {
            throw new EquipmentSaveEx("Error saving equipment.");
        }
    }

    @GetMapping()
    @Operation(summary = "Consult All Equipment.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success in processing the request."),
            @ApiResponse(responseCode = "401", description = "Unauthorized access."),
            @ApiResponse(responseCode = "404", description = "Requested resource not found."),
            @ApiResponse(responseCode = "500", description = "Unexpected system error."),
    })
    public ResponseEntity<List<EquipmentResponse>> consultAllEquipment() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getAllEquipments());
        } catch (Exception ex) {
            throw new EquipmentConsultEx("Error retrieving equipments.");
        }
    }

    @GetMapping("/{serial}")
    @Operation(summary = "Consult Equipment by serial.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success in processing the request."),
            @ApiResponse(responseCode = "401", description = "Unauthorized access."),
            @ApiResponse(responseCode = "404", description = "Requested resource not found."),
            @ApiResponse(responseCode = "500", description = "Unexpected system error."),
    })
    public ResponseEntity<EquipmentResponse> consultEquipmentBySerial(@PathVariable(value = "serial") String serial) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getEquipmentBySerial(serial));
    }

    @GetMapping("/{serial}/violations")
    @Operation(summary = "List violations of a equipment", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success in processing the request."),
            @ApiResponse(responseCode = "401", description = "Unauthorized access."),
            @ApiResponse(responseCode = "404", description = "Requested resource not found."),
            @ApiResponse(responseCode = "500", description = "Unexpected system error."),
    })
    public ResponseEntity<List<ViolationResponse>> consultViolationBySerialEquipment(@PathVariable(value = "serial") String serial,
                                                                               @RequestParam(value = "from", required = false) String from,
                                                                               @RequestParam(value = "to", required = false) String to) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getViolationBySerialEquipment(serial, from, to));
    }
}