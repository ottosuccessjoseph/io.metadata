package io.metadata.identity.domain.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@Data
public class WebResponse {
    private String timestamp;
    private String reference;

    public WebResponse(String reference) {
        this.setTimestamp(Instant.now().toString());
        this.setReference(reference);
    }

}
