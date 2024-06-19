package com.lucasmoraist.devideosapi.user.controller;

import com.lucasmoraist.devideosapi.user.dto.RegisterRequestDTO;
import com.lucasmoraist.devideosapi.user.dto.ResponseAuthDTO;
import com.lucasmoraist.devideosapi.user.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Register new user")
public class RegisterController {

    @Autowired
    private AuthService service;

    @Operation(
            summary = "Register new user",
            description = "Must create a user to access freely"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Must return email and token to access"
    )
    @PostMapping("register")
    public ResponseEntity<ResponseAuthDTO> register(@RequestBody RegisterRequestDTO dto) {
        var response = this.service.authRegister(dto);
        return ResponseEntity.ok().body(response);
    }
}
