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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class AuthServiceTest {

    @Mock
    private UserRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private TokenService tokenService;

    @InjectMocks
    private AuthService authService;

    @Test
    @DisplayName("Test authRegister with valid data")
    void case01() {
        RegisterRequestDTO dto = new RegisterRequestDTO("test", "test@test.com", "password");

        when(repository.findByEmail(dto.email())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(dto.password())).thenReturn("encodedPassword");
        when(tokenService.generateToken(any(User.class))).thenReturn("token");

        ResponseAuthDTO response = authService.authRegister(dto);

        assertEquals("test@test.com", response.email());
        assertEquals("token", response.token());
    }

    @Test
    @DisplayName("Test authLogin with valid credentials")
    void case02() {
        LoginRequestDTO dto = new LoginRequestDTO("test@test.com", "password");
        User user = User.builder()
                .email(dto.email())
                .password(dto.password())
                .build();

        when(repository.findByEmail(dto.email())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(dto.password(), user.getPassword())).thenReturn(true);
        when(tokenService.generateToken(user)).thenReturn("token");

        ResponseAuthDTO response = authService.authLogin(dto);

        assertEquals("test@test.com", response.email());
        assertEquals("token", response.token());
    }

    @Test
    @DisplayName("Test authLogin with non-existent email")
    void case03() {
        LoginRequestDTO dto = new LoginRequestDTO("nonexistent@test.com", "password");

        when(repository.findByEmail(dto.email())).thenReturn(Optional.empty());

        assertThrows(EmailNotFound.class, () -> authService.authLogin(dto));
    }

    @Test
    @DisplayName("Test authLogin with incorrect password")
    void case04() {
        LoginRequestDTO dto = new LoginRequestDTO("test@test.com", "incorrectPassword");
        User user = User.builder()
                .email(dto.email())
                .password("correctPassword")
                .build();

        when(repository.findByEmail(dto.email())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(dto.password(), user.getPassword())).thenReturn(false);

        assertThrows(PasswordException.class, () -> authService.authLogin(dto));
    }

    @Test
    @DisplayName("Test authRegister with duplicate email")
    void case05() {
        RegisterRequestDTO dto = new RegisterRequestDTO("test", "test@test.com", "password");
        User user = User.builder()
                .email(dto.email())
                .password(dto.password())
                .build();

        when(repository.findByEmail(dto.email())).thenReturn(Optional.of(user));

        assertThrows(DuplicateException.class, () -> authService.authRegister(dto));
    }
}