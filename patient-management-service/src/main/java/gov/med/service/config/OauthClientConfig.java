package gov.med.service.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
@EnableWebSecurity(debug = false)
public class OauthClientConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(requests -> requests.anyRequest().authenticated())
                .oauth2Login(login -> login
                        .redirectionEndpoint(endpoint -> endpoint
                            .baseUri("/login/oauth2/callback/*")
                        )
                        .successHandler(new SuccessHandler())
//                        .failureHandler(new FailureHandler())
                )
        ;
        return http.build();
    }
}
