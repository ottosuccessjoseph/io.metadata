package io.metadata.identity.domain.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Data;

@Data
public class AccessControl {
    private String reference;
    @Hidden
    private String roles;

}
