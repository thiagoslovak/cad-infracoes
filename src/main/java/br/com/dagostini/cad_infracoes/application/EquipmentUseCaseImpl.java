package br.com.dagostini.cad_infracoes.application;

import br.com.dagostini.cad_infracoes.application.exception.custom.EquipmentConsultEx;
import br.com.dagostini.cad_infracoes.application.exception.custom.EquipmentSaveEx;
import br.com.dagostini.cad_infracoes.application.exception.custom.ViolationConsultEx;
import br.com.dagostini.cad_infracoes.application.mappers.EquipmentMapper;
import br.com.dagostini.cad_infracoes.application.mappers.ViolationMapper;
import br.com.dagostini.cad_infracoes.core.EquipmentRequest;
import br.com.dagostini.cad_infracoes.core.EquipmentResponse;
import br.com.dagostini.cad_infracoes.core.ViolationResponse;
import br.com.dagostini.cad_infracoes.core.entities.Equipment;
import br.com.dagostini.cad_infracoes.core.entities.Violation;
import br.com.dagostini.cad_infracoes.core.usecases.EquipmentUseCase;
import br.com.dagostini.cad_infracoes.infra.EquipmentRepository;
import br.com.dagostini.cad_infracoes.infra.ViolationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class EquipmentUseCaseImpl implements EquipmentUseCase {

    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private final EquipmentRepository equipmentRepository;
    private final ViolationRepository violationRepository;
    private final EquipmentMapper equipmentMapper;
    private final ViolationMapper violationMapper;

    @Transactional
    public EquipmentResponse saveEquipment(EquipmentRequest equipmentRequest) {
        try {
            log.info("Saving equipment...");
            return equipmentMapper.toResponse(equipmentRepository.save(equipmentMapper.toEntity(equipmentRequest)));
        } catch (Exception ex) {
            log.error("Error saving equipment: {}", ex.getMessage());
            throw new EquipmentSaveEx("Error saving equipment.");
        }
    }

    @Transactional
    public List<EquipmentResponse> getAllEquipments() {
        try {
            log.info("Retrieving all equipments...");
            return equipmentRepository.findAll().stream()
                    .map(equipmentMapper::toResponse)
                    .toList();
        } catch (Exception ex) {
            log.error("Error retrieving equipments: {}", ex.getMessage());
            throw new EquipmentConsultEx("Error retrieving equipments.");
        }
    }

    @Transactional
    public EquipmentResponse getEquipmentBySerial(String serial) {
        try {
            log.info("Retrieving equipment by serial: {}", serial);
            return equipmentRepository.findBySerial(serial)
                    .map(equipmentMapper::toResponse)
                    .orElseThrow(() -> new EquipmentConsultEx("Equipment not found."));
        } catch (Exception ex) {
            log.error("Error retrieving equipment by serial: {}", ex.getMessage());
            throw new EquipmentConsultEx("Error retrieving equipment by serial.");
        }
    }

    @Transactional
    public List<ViolationResponse> getViolationBySerialEquipment(String serial, String from, String to) {
        log.info("Retrieving violation by serial equipment: {}", serial);
        List<Violation> violation;

        var equipment = equipmentRepository.findBySerial(serial);

        if (from == null || to == null) {
            violation = violationRepository.findViolationByEquipmentSerial(serial);

            return getViolationBySerialEquipmentValid(violation, equipment);
        }

        violation = violationRepository.findViolationAndEquipmentBetweenDates(serial, formatedDateToString(from),
                formatedDateToString(to));

        return getViolationBySerialEquipmentValid(violation, equipment);
    }

    private Date formatedDateToString(String date) {
        try {
            return new SimpleDateFormat(DATE_TIME_PATTERN).parse(date);
        } catch (ParseException ex) {
            throw new ViolationConsultEx("Error retrieving violation by serial equipment.");
        }
    }

    private List<ViolationResponse> getViolationBySerialEquipmentValid(List<Violation> violation, Optional<Equipment> equipment) {
        if (equipment.isEmpty() && violation.isEmpty()) {
            throw new ViolationConsultEx("Violation not found for the given equipment serial.");
        }

        List<ViolationResponse> violationResponses = violation.stream()
                .filter(v -> v.getEquipmentSerial().equals(equipment.get().getSerial()))
                .map(violationMapper::toResponse)
                .collect(Collectors.toList());

        if (violationResponses.isEmpty()) {
            throw new ViolationConsultEx("Violation not found for the given equipment serial.");
        }

        return violationResponses;
    }
}