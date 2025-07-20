package br.com.dagostini.cad_infracoes.application;

import br.com.dagostini.cad_infracoes.application.exception.custom.UserConsultEx;
import br.com.dagostini.cad_infracoes.application.exception.custom.UserSaveEx;
import br.com.dagostini.cad_infracoes.application.mappers.UserMapper;
import br.com.dagostini.cad_infracoes.core.AuthenticationRequest;
import br.com.dagostini.cad_infracoes.core.RegisterRequest;
import br.com.dagostini.cad_infracoes.core.UserResponse;
import br.com.dagostini.cad_infracoes.core.entities.User;
import br.com.dagostini.cad_infracoes.core.usecases.UserUserCase;
import br.com.dagostini.cad_infracoes.infra.UserRepository;
import br.com.dagostini.cad_infracoes.infra.security.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserUseCaseImpl implements UserUserCase {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Transactional
    public UserResponse saveUser(RegisterRequest data) {
        try {
            log.info("Saving users...");
            String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
            User newUser = new User(data.getLogin(), encryptedPassword, data.getRole());

            return userMapper.toResponse(userRepository.save(newUser));
        } catch (Exception ex) {
            log.error("Error saving user: {}", ex.getMessage());
            throw new UserSaveEx("Error saving user.");
        }
    }

    public void validateUserDetailsByLogin(String login) {
        if (userRepository.findByLogin(login).isPresent()) {
            throw new UserConsultEx("UserDetails exists.");
        }
    }

    public String getTokenByAuthenticationRequest(AuthenticationRequest data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getLogin(), data.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        return tokenService.generateToken((User) auth.getPrincipal());
    }
}