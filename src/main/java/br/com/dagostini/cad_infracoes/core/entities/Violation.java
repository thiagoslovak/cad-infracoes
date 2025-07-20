package br.com.dagostini.cad_infracoes.core.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_violation")
public class Violation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "equipment_serial", nullable = false)
    private String equipmentSerial;

    @Column(name = "occurrence_date_utc")
    private Date occurrenceDateUtc;

    @DecimalMin(value = "0")
    @Column(name = "measured_speed", nullable = false)
    private Double measuredSpeed;

    @DecimalMin(value = "0")
    @Column(name = "considered_speed", nullable = false)
    private Double consideredSpeed;

    @DecimalMin(value = "0")
    @Column(name = "regulated_speed", nullable = false)
    private Double regulatedSpeed;

    //OBS: Deve ser armazeado no blob storage para ter um custo menor na operação comparado com o banco de dados.
    // E pensando e larga escala, o blob storage é mais eficiente para armazenar arquivos grandes como imagens.
    @Column(name = "picture")
    private String picture;

    @Column(name = "type")
    private Type type;

    @Version
    @Column(name = "version")
    private Timestamp version;
}