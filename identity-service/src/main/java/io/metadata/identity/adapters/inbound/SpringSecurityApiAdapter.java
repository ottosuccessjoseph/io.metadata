package io.metadata.identity.adapters.inbound;

import io.metadata.identity.domain.dto.CreateUserRequest;
import io.metadata.identity.domain.dto.LoginRequest;
import io.metadata.identity.domain.dto.RefreshTokenRequest;
import io.metadata.identity.domain.dto.WebResponse;
import io.metadata.identity.ports.inbound.WebApiPort;
import io.metadata.identity.verbs.CreateUserVerb;
import io.metadata.identity.verbs.LoginVerb;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/identity/auth")
@RequiredArgsConstructor
public class SpringSecurityApiAdapter implements WebApiPort {

    private final CreateUserVerb createUserVerb;
    private final LoginVerb loginVerb;
    @Override
    @PostMapping("/signup")
    public WebResponse users(@RequestHeader Map<String, String> headers,
                             @RequestBody CreateUserRequest request) {
        return createUserVerb.execute(request);
    }

    @Override
    @PostMapping("/login")
    public WebResponse login(@RequestHeader Map<String, String> headers,
                             @RequestBody LoginRequest request) {
        return loginVerb.execute(request);
    }

    @Override
    public void logout(Map<String, String> headers, HttpServletRequest request) {

    }

    @Override
    public WebResponse refreshToken(Map<String, String> headers, RefreshTokenRequest request) {
        return null;
    }
}
