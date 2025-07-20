package br.com.dagostini.cad_infracoes.application.mappers;

import br.com.dagostini.cad_infracoes.core.EquipmentRequest;
import br.com.dagostini.cad_infracoes.core.EquipmentResponse;
import br.com.dagostini.cad_infracoes.core.entities.Equipment;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EquipmentMapper {

    public Equipment toEntity(EquipmentRequest equipmentRequest) {
        var modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        return modelMapper.map(equipmentRequest, Equipment.class);
    }

    public EquipmentResponse toResponse(Equipment equipment) {
        var modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        return modelMapper.map(equipment, EquipmentResponse.class);
    }
}
