package io.metadata.student.domain.exceptions;

import io.metadata.student.domain.dto.Error;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public class BusinessException extends RuntimeException {

    private String reference;
    private List<Error> errors;
    private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    public BusinessException(String reference, List<Error> errors) {
        this.reference = reference;
        this.errors = errors;
    }

    public BusinessException(String reference, List<Error> errors, HttpStatus httpStatus) {
        this.reference = reference;
        this.errors = errors;
        this.httpStatus = httpStatus;
    }
}
