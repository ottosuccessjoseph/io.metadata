package io.metadata.course.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class WebResponse {
    private String reference;
    private String timestamp;

    public WebResponse(String reference) {
        this.reference = reference;
        this.timestamp = Instant.now().toString();
    }

}
