package br.com.dagostini.cad_infracoes.application.mappers;

import br.com.dagostini.cad_infracoes.core.ViolationRequest;
import br.com.dagostini.cad_infracoes.core.ViolationResponse;
import br.com.dagostini.cad_infracoes.core.entities.Violation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ViolationMapper {

    public Violation toEntity(ViolationRequest violationRequest) {
        var modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        return modelMapper.map(violationRequest, Violation.class);
    }

    public ViolationResponse toResponse(Violation violation) {
        var modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        return modelMapper.map(violation, ViolationResponse.class);
    }
}