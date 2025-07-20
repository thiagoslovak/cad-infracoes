package br.com.dagostini.cad_infracoes.application.mappers;

import br.com.dagostini.cad_infracoes.core.UserResponse;
import br.com.dagostini.cad_infracoes.core.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse toResponse(User user) {
        var modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        return modelMapper.map(user, UserResponse.class);
    }
}