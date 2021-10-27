package io.metadata.identity.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RefreshTokenRequest extends AccessControl{
    @JsonProperty("refresh_token")
    private String refreshToken;
}
