package br.com.dagostini.cad_infracoes.core.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_equipment")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "serial", nullable = false, unique = true)
    private String serial;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "address", nullable = false)
    private String address;

    @DecimalMin(value = "-90.0") @DecimalMax(value = "90.0")
    @Column(name = "latitude")
    private Double latitude;

    @DecimalMin(value = "-180.0") @DecimalMax(value = "180.0")
    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "active")
    private Boolean active;

    @Version
    @Column(name = "version")
    private Timestamp version;
}