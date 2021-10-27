package io.metadata.course.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterStudentRequest extends AccessControl{
    private String reference;
    private String userReference;
}
