package com.lucasmoraist.devideosapi.user.controller;

import com.lucasmoraist.devideosapi.user.dto.LoginRequestDTO;
import com.lucasmoraist.devideosapi.user.dto.ResponseAuthDTO;
import com.lucasmoraist.devideosapi.user.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Slf4j
@Tag(name = "User login")
public class LoginController {

    @Autowired
    private AuthService service;

    @Operation(
            summary = "Login of the user",
            description = "For user to log in to the platform"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Must return email and token to access"
    )
    @PostMapping("login")
    public ResponseEntity<ResponseAuthDTO> login(@Valid @RequestBody LoginRequestDTO dto) {
        var response = this.service.authLogin(dto);
        log.info("Logging in");
        return ResponseEntity.ok().body(response);
    }

}
