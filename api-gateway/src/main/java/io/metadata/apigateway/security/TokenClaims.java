package io.metadata.apigateway.security;

import lombok.Data;

import java.util.List;

@Data
public class TokenClaims {
    private String userReference;
    private String userName;
    private String jti;
    private List<String> authorities;
}
