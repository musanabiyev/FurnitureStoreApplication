package com.company.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Data
public class AuthRequest {

    @NotNull
//    @Min(value = 5, message = "username must be at least 5 characters")
    private String username;

    @NotNull
//    @Min(value = 5, message = "password must be at least 5 characters")
    private String password;

}