package br.com.dagostini.cad_infracoes.core.usecases;

public interface ViolationUseCase {
    boolean validateEquipmentIsActive(String equipmentSerial);
}
