package gov.med.service.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

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
                        .failureHandler((request, response, exception) ->
                            exception.printStackTrace()
                        )
                )
        ;
        return http.build();
    }
}
