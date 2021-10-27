package io.metadata.student.domain.dto;

import lombok.Data;

@Data
public class DataWebResponse<T> extends WebResponse{

    private T data;
    public DataWebResponse(String reference, T data) {
        super(reference);
        this.data = data;
    }
}
