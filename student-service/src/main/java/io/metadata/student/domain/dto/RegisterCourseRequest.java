package io.metadata.student.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RegisterCourseRequest extends AccessControl {
    private String studentReference;
    @JsonProperty("reference")
    private String courseReference;
}
