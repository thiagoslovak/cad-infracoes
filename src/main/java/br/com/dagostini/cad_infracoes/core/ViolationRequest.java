package br.com.dagostini.cad_infracoes.core;

import br.com.dagostini.cad_infracoes.core.entities.Type;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ViolationRequest {

    private String equipmentSerial;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date occurrenceDateUtc;

    private Double measuredSpeed;
    private Double consideredSpeed;
    private Double regulatedSpeed;
    private String picture;
    private Type type;
}