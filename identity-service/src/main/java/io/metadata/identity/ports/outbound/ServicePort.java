package io.metadata.identity.ports.outbound;

import io.metadata.identity.domain.dto.AccessControl;
import io.metadata.identity.domain.dto.WebResponse;

import java.util.Map;
import java.util.Optional;

public interface ServicePort<T extends AccessControl> {
    Optional<WebResponse> create(Map<String, String> headers, T request);
}
