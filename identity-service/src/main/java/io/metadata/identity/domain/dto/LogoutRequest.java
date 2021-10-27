package io.metadata.identity.domain.dto;

import lombok.Data;

@Data
public class LogoutRequest extends AccessControl {
    private String username;
}
