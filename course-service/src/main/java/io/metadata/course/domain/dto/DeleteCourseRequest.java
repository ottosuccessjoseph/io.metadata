package io.metadata.course.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteCourseRequest extends AccessControl{
    private String reference;
}
