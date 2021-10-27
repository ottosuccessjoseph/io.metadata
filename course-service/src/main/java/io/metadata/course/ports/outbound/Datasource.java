package io.metadata.course.ports.outbound;

import java.util.Optional;

public interface Datasource<T> {
    Optional<T> findByReference(String reference);

    T save(T model);
    void delete(T model);
}

