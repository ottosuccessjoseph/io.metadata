package io.metadata.apigateway.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
@Slf4j
public class AuthenticationManager implements ReactiveAuthenticationManager {

    private final JwtTokenProvider tokenProvider;

    public AuthenticationManager(JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String authToken = tokenProvider.resolveToken(authentication.getCredentials().toString());
        log.debug("processing token: {}", authToken);
        if (tokenProvider.validateToken(authToken)) {
            TokenClaims claims = tokenProvider.getTokenClaims(authToken);
            String username = claims.getUserName();
            log.info("valid token, user {} is logged-in", username);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    username, null, tokenProvider.getAuthority(authToken));
            return Mono.just(auth);

        }
        return Mono.empty();
    }

    public Mono<Authentication> authenticate(ServerWebExchange swe) {
        String authToken = tokenProvider.resolveToken(swe);
        if (authToken == null) {
            log.debug("No valid token found for authentication");
            return Mono.empty();
        }

       return authenticate(new UsernamePasswordAuthenticationToken(authToken, authToken));
    }
}
