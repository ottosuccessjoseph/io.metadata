package io.metadata.apigateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class WebSecurity {

    private final SecurityContextRepository securityContextRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public WebSecurity(SecurityContextRepository securityContextRepository, JwtTokenProvider jwtTokenProvider) {
        this.securityContextRepository = securityContextRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /**
     * Security filter that pipes request based on authentication and authorization
     */
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
//                .addFilterAt(new MutateRequestHeaderFilter(jwtTokenProvider), SecurityWebFiltersOrder.FIRST)
                .exceptionHandling()
                .authenticationEntryPoint((swe, e) -> Mono.fromRunnable(() -> {
                    swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                })).accessDeniedHandler((swe, e) -> Mono.fromRunnable(() -> {
                    swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                })).and()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .authenticationManager(securityContextRepository.getAuthenticationManager())
                .securityContextRepository(securityContextRepository)
                .authorizeExchange()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .pathMatchers(HttpMethod.POST, "/students/**").permitAll()
                .pathMatchers(HttpMethod.GET, "/students/**").permitAll()
                .pathMatchers(HttpMethod.PUT, "/students/**").permitAll()
                .pathMatchers(HttpMethod.DELETE, "/students/**").permitAll()
                .pathMatchers(HttpMethod.POST, "/identity/auth/signup", "/identity/auth/login").permitAll()
                .pathMatchers(HttpMethod.GET, "/actuator/health").permitAll()
                .pathMatchers(HttpMethod.PUT, "/courses/**").permitAll()
                .pathMatchers(HttpMethod.POST, "/courses/**").permitAll()
                .pathMatchers(HttpMethod.DELETE, "/courses/**").permitAll()
                .pathMatchers(HttpMethod.GET, "/courses/{course_reference}").permitAll()
                .pathMatchers(HttpMethod.GET, "/courses/{course_reference}/students", "/courses/students/{reference}", "/courses").hasAuthority(Role.ROLE_ADMIN.id())
                .anyExchange()
                .authenticated()
                .and().build();
    }
}