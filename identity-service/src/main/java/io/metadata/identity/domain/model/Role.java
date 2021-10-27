package io.metadata.identity.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements GrantedAuthority {
    public final static String ROLE_STUDENT = "STUDENT";
    public final static String ROLE_ADMIN = "ADMIN";
    private String authority;

}
