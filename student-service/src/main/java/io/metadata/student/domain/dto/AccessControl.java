package io.metadata.student.domain.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccessControl {
    @Hidden
    private String _username;
    @Hidden
    private String _email;
}
