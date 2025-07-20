package br.com.dagostini.cad_infracoes.infra;

import br.com.dagostini.cad_infracoes.core.entities.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, UUID> {

    Optional<Equipment> findBySerial(String serial);
}