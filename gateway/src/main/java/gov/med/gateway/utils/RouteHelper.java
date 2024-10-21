package gov.med.gateway.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum RouteHelper {
    SERVICE1_ROUTE("service1_route", "http://localhost:8081"),
    ACCOUNT_ROUTE("account_route", "http://localhost:8082")
    ;

    private String pathRoute;
    private String url;

    RouteHelper(String pathRoute, String url) {
        this.pathRoute = pathRoute;
        this.url = url;
    }
}
