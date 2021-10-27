package io.metadata.course.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentsWithSpecificCourseRequest extends AccessControl {
    private String reference;
}
