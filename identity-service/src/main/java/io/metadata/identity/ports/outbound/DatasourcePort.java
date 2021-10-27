package io.metadata.identity.ports.outbound;

import java.util.Optional;

public interface DatasourcePort<T> {
    T save(T domain);
    Optional<T> findByUsername(String username);
    Optional<T> findById(String id);
}
