package io.metadata.identity.adapters.outbound;

import io.metadata.identity.domain.dto.AccessControl;
import io.metadata.identity.domain.dto.WebResponse;
import io.metadata.identity.ports.outbound.ServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceAdapter implements ServicePort<AccessControl> {

    private final WebClient webClient;

    @Override
    public Optional<WebResponse> create(Map<String, String> headers, AccessControl request) {
        URI studentUri = UriComponentsBuilder.fromUriString("http://localhost:10100")
                .path("/students")
                .build().toUri();

        WebResponse webResponse = webClient.post()
                .uri(studentUri)
                .body(Mono.just(request), AccessControl.class)
                .exchangeToMono(response -> {
                    if (response.statusCode().is2xxSuccessful()) {
                        return response.bodyToMono(WebResponse.class);
                    } else {
                        return Mono.empty();
                    }
                })
                .block();
        return Objects.nonNull(webResponse) ? Optional.of(webResponse) : Optional.empty();
    }
}
