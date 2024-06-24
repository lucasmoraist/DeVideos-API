package com.lucasmoraist.devideosapi.user.service;

import com.lucasmoraist.devideosapi.exception.DuplicateException;
import com.lucasmoraist.devideosapi.exception.EmailNotFound;
import com.lucasmoraist.devideosapi.exception.PasswordException;
import com.lucasmoraist.devideosapi.infra.security.TokenService;
import com.lucasmoraist.devideosapi.user.domain.User;
import com.lucasmoraist.devideosapi.user.dto.LoginRequestDTO;
import com.lucasmoraist.devideosapi.user.dto.RegisterRequestDTO;
import com.lucasmoraist.devideosapi.user.dto.ResponseAuthDTO;
import com.lucasmoraist.devideosapi.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public ResponseAuthDTO authLogin(LoginRequestDTO dto) {
        User user = this.repository.findByEmail(dto.email())
                .orElseThrow(() -> new EmailNotFound("Email incorreto"));

        if (!passwordEncoder.matches(dto.password(), user.getPassword())) throw new PasswordException("Senha incorreta");

        return this.token(user);
    }

    public ResponseAuthDTO authRegister(RegisterRequestDTO dto) {
        Optional<User> optionalUser = this.repository.findByEmail(dto.email());
        if (optionalUser.isPresent()) throw new DuplicateException("Este email já existe");

        User newUser = this.instanceUser(dto);

        this.repository.save(newUser);

        return this.token(newUser);
    }

    private ResponseAuthDTO token(User user) {
        String token = this.tokenService.generateToken(user);
        return new ResponseAuthDTO(user.getEmail(), token);
    }

    private User instanceUser(RegisterRequestDTO dto){
        if(dto.name() == null || dto.name().isEmpty() || dto.email() == null || dto.email().isEmpty() || dto.password() == null || dto.password().isEmpty()){
            throw new IllegalArgumentException("Invalid data");
        }

        return User.builder()
                .name(dto.name())
                .email(dto.email())
                .password(this.passwordEncoder.encode(dto.password()))
                .build();
    }

}
