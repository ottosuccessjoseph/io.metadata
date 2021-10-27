package io.metadata.course.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class ErrorWebResponse extends WebResponse{
    private List<Error> errors;

    public ErrorWebResponse(String reference, List<Error> errors) {
        super(reference);
        this.errors = errors;
    }
}
