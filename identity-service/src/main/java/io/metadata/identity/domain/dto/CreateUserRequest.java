package io.metadata.identity.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.metadata.identity.domain.model.Role;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CreateUserRequest extends AccessControl {
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    private String email;
    @NotEmpty(message = "username is required")
    @JsonProperty("username")
    private String username;
    @NotEmpty(message = "password is required")
    @JsonProperty("password")
    private String password;
    private Role role;
}
