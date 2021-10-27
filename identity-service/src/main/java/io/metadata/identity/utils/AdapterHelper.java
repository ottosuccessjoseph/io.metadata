package io.metadata.identity.utils;

import io.metadata.identity.domain.dto.AccessControl;

import java.util.Map;

public class AdapterHelper {

    public static AccessControl fromHeaders(Map<String, String> headers) {
        AccessControl accessControl = new AccessControl();
        accessControl.setRoles(headers.get("x-metadata-roles"));
        return accessControl;
    }
}
