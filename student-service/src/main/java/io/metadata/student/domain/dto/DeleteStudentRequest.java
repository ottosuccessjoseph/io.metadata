package io.metadata.student.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteStudentRequest extends AccessControl {
    private String reference;
}
