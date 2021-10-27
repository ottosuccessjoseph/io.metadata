package io.metadata.student.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReadStudentRequest extends AccessControl{
    private String reference;
}
