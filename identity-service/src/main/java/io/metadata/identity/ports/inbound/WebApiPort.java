package io.metadata.identity.ports.inbound;

import io.metadata.identity.domain.dto.CreateUserRequest;
import io.metadata.identity.domain.dto.LoginRequest;
import io.metadata.identity.domain.dto.RefreshTokenRequest;
import io.metadata.identity.domain.dto.WebResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface WebApiPort {
    WebResponse users(Map<String, String> headers, CreateUserRequest request);
    WebResponse login(Map<String, String> headers, LoginRequest request);
    void logout(Map<String, String> headers, HttpServletRequest request);
    WebResponse refreshToken(Map<String, String> headers, RefreshTokenRequest request);
}
