package com.company.security.dto;

import com.company.dto.response.ApiMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JWTResponse {

    private AuthResponse data;

    private ApiMessage status;

}
