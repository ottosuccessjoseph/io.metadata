package io.metadata.identity.verbs;

import io.metadata.identity.domain.dto.AccessControl;
import io.metadata.identity.domain.dto.Error;
import io.metadata.identity.domain.dto.WebResponse;
import io.metadata.identity.domain.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

@Slf4j
public abstract class AbstractVerb<T extends AccessControl, R extends WebResponse> {

    public R execute(T request) {
        R response = null;
        String reference = UUID.randomUUID().toString();
        try {
            List<Error> errors = validate(request);
            if (errors != null && errors.size() > 0) {
                throw new BusinessException(reference, errors);
            }
            response = handler(request);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
        return response;
    }

    protected abstract R handler(T request);
    protected abstract List<Error> validate(T request);
}
