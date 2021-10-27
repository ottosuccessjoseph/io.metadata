package io.metadata.student.ports.outbound;

import java.util.Optional;

public interface Datasource<T> {
    Optional<T> findByReference(String reference);
    T save(T model);
    void delete(T model);
}

