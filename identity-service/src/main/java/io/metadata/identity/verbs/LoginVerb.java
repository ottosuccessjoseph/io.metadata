package io.metadata.identity.verbs;

import io.metadata.identity.domain.dto.DataWebResponse;
import io.metadata.identity.domain.dto.Error;
import io.metadata.identity.domain.dto.LoginRequest;
import io.metadata.identity.domain.dto.LoginResponse;
import io.metadata.identity.domain.model.User;
import io.metadata.identity.security.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginVerb extends AbstractVerb<LoginRequest, DataWebResponse> {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    @Value("${metadata.srs.token.access.expiresInMs}")
    private int accessTokenExpiresInMs;
    @Value("${metadata.srs.token.refresh.expiresInMs}")
    private int refreshTokenExpiresInMs;
    @Override
    protected DataWebResponse handler(LoginRequest request) {
        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        User user = (User) authenticate.getPrincipal();
        String token = jwtTokenUtil.generateAccessToken(user);
        String refreshToken = jwtTokenUtil.generateRefreshToken(user);

        return new DataWebResponse(user.getReference(), LoginResponse.builder()
                .accessToken(token)
                .refreshToken(refreshToken)
                .accessTokenExpiresInMs(accessTokenExpiresInMs)
                .refreshTokenExpiresInMs(refreshTokenExpiresInMs)
                .build());
    }

    @Override
    protected List<Error> validate(LoginRequest request) {
        return null;
    }
}
