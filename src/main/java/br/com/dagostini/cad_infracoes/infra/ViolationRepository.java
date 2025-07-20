package br.com.dagostini.cad_infracoes.infra;

import br.com.dagostini.cad_infracoes.core.entities.Violation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface ViolationRepository extends JpaRepository<Violation, UUID> {

    @Query(value = "SELECT * FROM tb_violation WHERE equipment_serial = :equipmentSerial AND occurrence_date_utc BETWEEN :from AND :to", nativeQuery = true)
    List<Violation> findViolationAndEquipmentBetweenDates(@Param("equipmentSerial") String equipmentSerial,
                                                          @Param("from") Date from,
                                                          @Param("to") Date to);

    List<Violation> findViolationByEquipmentSerial(String equipmentSerial);
}