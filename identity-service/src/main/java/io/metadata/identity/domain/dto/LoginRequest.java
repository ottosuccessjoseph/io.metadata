package io.metadata.identity.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginRequest extends AccessControl {
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
}
