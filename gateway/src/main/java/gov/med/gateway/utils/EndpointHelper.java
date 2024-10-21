package gov.med.gateway.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EndpointHelper {
    SERVICE_01("/service1/**"),
    ACCOUNT_USER("/account/**")
    ;

    private String path;

    EndpointHelper(String path) {
        this.path = path;
    }
}
