package com.company.controller;

import com.company.dto.request.CreateUserDTORequest;
import com.company.security.dto.AuthRequest;
import com.company.security.dto.JWTResponse;
import com.company.security.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/auth")
public class AuthenticationResourceController {


    private final AuthenticationService authenticationService;

    public AuthenticationResourceController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signin")
    public ResponseEntity<JWTResponse> login(
            @Valid @RequestBody AuthRequest authenticationDTORequest) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(authenticationService.createAuthenticationToken(authenticationDTORequest));
    }

}
