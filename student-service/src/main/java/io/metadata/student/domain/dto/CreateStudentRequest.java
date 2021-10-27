package io.metadata.student.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class CreateStudentRequest extends AccessControl {
    private String reference;
    @NotNull(message = "first name is required")
    @JsonProperty("first_name")
    private String firstName;
    @NotNull(message = "last name is required")
    @JsonProperty("last_name")
    private String lastName;
    @NotNull(message = "email is required")
    @Email(message = "email is invalid")
    private String email;
    @NotNull(message = "username is required")
    private String username;
}
