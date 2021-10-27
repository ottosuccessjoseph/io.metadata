package io.metadata.course.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Error {
    private String source;
    private String field;
    private String message;
}
