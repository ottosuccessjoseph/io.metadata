package io.metadata.course.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreateCourseRequest extends AccessControl {
    @NotEmpty
    @NotNull(message = "course name cannot be empty")
    private String name;
}
