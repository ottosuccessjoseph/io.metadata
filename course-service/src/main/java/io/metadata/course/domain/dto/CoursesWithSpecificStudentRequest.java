package io.metadata.course.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoursesWithSpecificStudentRequest extends AccessControl{
    private String reference;
}
