package io.metadata.course.domain.exceptions;

import io.metadata.course.domain.dto.Error;
import io.metadata.course.domain.dto.ErrorWebResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final static String SOURCE = "identity-service";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        //Get all errors
        List<Error> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> new Error(SOURCE, x.getField(), x.getDefaultMessage()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(new ErrorWebResponse(new Date().toString(), errors), headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorWebResponse> handleBusinessException(BusinessException ex,
                                                                       WebRequest request) {
        String reference = StringUtils.hasText(ex.getReference()) ? ex.getReference() : "";
        return new ResponseEntity<>(new ErrorWebResponse(reference, ex.getErrors()), ex.getHttpStatus());
    }
//
//    @ExceptionHandler(MissingRequestHeaderException.class)
//    protected ResponseEntity<ErrorResponse> handleRequestHeaderException(MissingRequestHeaderException ex, WebRequest request) {
//        return new ResponseEntity<>(new ErrorResponse(new Date().toString(), request.getHeader("x-angularpay-user-reference"),
//                Arrays.asList(new Error("VALIDATION_ERROR", SOURCE, ex.getHeaderName(), ex.getMessage()))), HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(InvalidFormatException.class)
//    protected ResponseEntity<Error> handleInvalidFormatException(InvalidFormatException ex, WebRequest request) {
//        return new ResponseEntity<>(new ErrorResponse(new Date().toString(), request.getHeader("x-angularpay-user-reference"),
//                Arrays.asList(new Error("VALIDATION_ERROR", SOURCE, ex.getPath().toString(), ex.getMessage()))), HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<ErrorWebResponse> handleConstraintViolation(
            ConstraintViolationException ex,
            WebRequest request) {
        List<Error> errors = ex.getConstraintViolations()
                .parallelStream()
                .map(x -> {
                    Iterator i = x.getPropertyPath().iterator();
                    String field = i.next().toString();
                    while (i.hasNext()) {
                        field = i.next().toString();
                    }

                    return new Error(SOURCE, field, x.getMessage());
                })
                .collect(Collectors.toList());

        ErrorWebResponse error = new ErrorWebResponse(UUID.randomUUID().toString(), errors);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
