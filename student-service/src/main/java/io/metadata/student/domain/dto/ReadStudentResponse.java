package io.metadata.student.domain.dto;

import lombok.Data;

@Data
public class ReadStudentResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
}
