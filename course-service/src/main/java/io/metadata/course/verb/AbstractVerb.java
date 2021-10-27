package io.metadata.course.verb;

import io.metadata.course.domain.dto.Error;
import io.metadata.course.domain.exceptions.BusinessException;

import java.util.List;
import java.util.UUID;

public abstract class AbstractVerb<T, R> {

    public R execute(T request) {
        R response = null;
        try {
            List<Error> errors = validate(request);
            if (errors != null && errors.size() > 0) {
                throw new BusinessException(UUID.randomUUID().toString(), errors);
            }
            response = handler(request);
        } catch (Exception e) {
            throw e;
        }
        return response;
    }

    protected abstract R handler(T request);

    protected abstract List<Error> validate(T request);
}
