package gov.med.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static gov.med.gateway.utils.EndpointHelper.ACCOUNT_USER;
import static gov.med.gateway.utils.EndpointHelper.SERVICE_01;
import static gov.med.gateway.utils.RouteHelper.ACCOUNT_ROUTE;
import static gov.med.gateway.utils.RouteHelper.SERVICE1_ROUTE;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(SERVICE1_ROUTE.getPathRoute(), r -> r.path(SERVICE_01.getPath())
                        .filters(f -> f
                                .circuitBreaker(c -> c
                                        .setFallbackUri("forward:/fallback"))
                        )
                        .uri(SERVICE1_ROUTE.getUrl())
                )
                .route(ACCOUNT_ROUTE.getPathRoute(), r -> r.path(ACCOUNT_USER.getPath())
                        .uri(ACCOUNT_ROUTE.getUrl()))
                .build();
    }
}
