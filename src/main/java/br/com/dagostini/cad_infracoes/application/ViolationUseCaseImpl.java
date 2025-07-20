package br.com.dagostini.cad_infracoes.application;

import br.com.dagostini.cad_infracoes.application.exception.custom.EquipmentConsultEx;
import br.com.dagostini.cad_infracoes.application.exception.custom.ViolationConsultEx;
import br.com.dagostini.cad_infracoes.application.exception.custom.ViolationSaveEx;
import br.com.dagostini.cad_infracoes.application.mappers.ViolationMapper;
import br.com.dagostini.cad_infracoes.core.ViolationRequest;
import br.com.dagostini.cad_infracoes.core.ViolationResponse;
import br.com.dagostini.cad_infracoes.core.usecases.ViolationUseCase;
import br.com.dagostini.cad_infracoes.infra.EquipmentRepository;
import br.com.dagostini.cad_infracoes.infra.ViolationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ViolationUseCaseImpl implements ViolationUseCase {

    private final ViolationRepository violationRepository;
    private final ViolationMapper violationMapper;
    private final EquipmentRepository equipmentRepository;

    @Transactional
    public ViolationResponse saveViolation(ViolationRequest violationRequest) {
        try {
            log.info("Saving violation...");

            if (!validateEquipmentIsActive(violationRequest.getEquipmentSerial())) {;
                log.error("Equipment with serial {} is not active.", violationRequest.getEquipmentSerial());
                throw new EquipmentConsultEx("Equipment is not active.");
            }

            return violationMapper.toResponse(violationRepository.save(violationMapper.toEntity(violationRequest)));
        } catch (Exception ex) {
            log.error("Error saving violation: {}", ex.getMessage());
            throw new ViolationSaveEx("Error saving violation.");
        }
    }

    @Transactional
    public ViolationResponse getViolationById(String id) {
        try {
            log.info("Retrieving violation by id: {}", id);
            return violationRepository.findById(UUID.fromString(id))
                    .map(violationMapper::toResponse)
                    .orElseThrow(() -> new ViolationConsultEx("Violation not found."));
        } catch (Exception ex) {
            log.error("Error retrieving violation by id: {}", ex.getMessage());
            throw new ViolationConsultEx("Error retrieving violation by id.");
        }
    }

    @Override
    public boolean validateEquipmentIsActive(String equipmentSerial) {
        var equipment = equipmentRepository.findBySerial(equipmentSerial)
                .orElseThrow(() -> new EquipmentConsultEx("Equipment not found."));

        return equipment.getActive();
    }
}