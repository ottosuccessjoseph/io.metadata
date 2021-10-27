package io.metadata.apigateway.security;

public enum Role {
    ROLE_STUDENT("STUDENT"),
    ROLE_ADMIN("ADMIN");

    private final String id;

    Role(String id) {
        this.id = id;
    }

    public String id() {
        return id;
    }
}
