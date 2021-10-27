package io.metadata.course.domain.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccessControl {
    private String reference_;
    @Hidden
    private String _username;
    @Hidden
    private String _email;
}
