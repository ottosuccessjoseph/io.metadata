package io.metadata.identity.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("access_token_expires_in_ms")
    private int accessTokenExpiresInMs;
    @JsonProperty("refresh_token")
    private String refreshToken;
    @JsonProperty("refresh_token_expires_in_ms")
    private int refreshTokenExpiresInMs;
}
