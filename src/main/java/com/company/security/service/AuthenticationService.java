package com.company.security.service;


import com.company.dto.response.ApiMessage;
import com.company.exception.AuthenticationException;
import com.company.exception.TokenNotFoundException;
import com.company.security.dto.AuthRequest;
import com.company.security.dto.AuthResponse;
import com.company.security.dto.JWTResponse;
import com.company.security.model.JwtUser;
import com.company.security.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;

    public JWTResponse createAuthenticationToken(AuthRequest request) {
        authenticate(request.getUsername(), request.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return new JWTResponse(
                new AuthResponse(token),
                ApiMessage.builder()
                        .statusCode(HttpStatus.OK.value())
                        .statusMessage("Token was successfully created")
                        .responseAt(LocalDateTime.now())
                        .build()
        );
    }

    public JwtUser getUserByToken(String authToken) {
        if (Objects.isNull(authToken) || authToken.length() < 7)
            throw new TokenNotFoundException("cant get user by token");

        String token = authToken.substring(7);

        String username = jwtTokenUtil.getUsernameFromToken(token);
        return (JwtUser) userDetailsService.loadUserByUsername(username);
    }

    private void authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new AuthenticationException("user is disabled", e);
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("bad credentials", e);
        }
    }
}