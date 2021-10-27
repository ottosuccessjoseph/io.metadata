package io.metadata.identity.domain.dto;

import lombok.Data;

@Data
public class DataWebResponse<T> extends WebResponse {

    private T data;
    public DataWebResponse(String reference, T response) {
        super(reference);
        this.data = response;
    }
}
