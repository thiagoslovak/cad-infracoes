package br.com.dagostini.cad_infracoes.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentResponse {

    private UUID id;
    private String serial;
    private String model;
    private String address;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Boolean active;
}