package io.metadata.apigateway.health;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Optional;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Component
@Slf4j
public class HealthCheck implements HealthIndicator {
    private static final String HEALTH_CHECK = "healthcheck";

    private final RouteLocator routeLocator;

    public HealthCheck(RouteLocator routeLocator) {
        this.routeLocator = routeLocator;
    }

    @Override
    public Health health() {
        Health.Builder healthBuilder = Health.up();
        routeLocator.getRoutes().parallel().subscribe(route -> {
            Optional<String> healthCheckUrl = getHealthCheckUrl(route);
            if (healthCheckUrl.isPresent()) {
                boolean healthy = checkUrl(healthCheckUrl.get());
                healthBuilder.withDetail(route.getId(), healthy);
                if (!healthy) {
                    healthBuilder.down();
                }
            }
        });
        Health health = healthBuilder.build();
        log.info("System Health : {}", kv("health", health));
        return health;
    }

    private Optional<String> getHealthCheckUrl(Route route) {
        Object result = route.getMetadata().get(HEALTH_CHECK);
        if (result == null) return Optional.empty();
        return Optional.of(route.getUri() + result.toString());
    }

    private boolean checkUrl(String url) {
        ResponseEntity<String> response;
        try {
            response = restTemplate().getForEntity(url, String.class);
        } catch (RestClientException e) {
            return false;
        }
        return response.getStatusCode() == HttpStatus.OK;
    }

    private RestTemplate restTemplate() {
        return new RestTemplateBuilder().setConnectTimeout(Duration.ofSeconds(5)).build();
    }
}