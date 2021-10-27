package io.metadata.course.domain.dto;

import lombok.Data;

@Data
public class UpdateCourseRequest {
    private String reference;
    private String name;
}
