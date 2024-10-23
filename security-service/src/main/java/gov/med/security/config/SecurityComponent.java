package gov.med.security.config;

import gov.med.security.web.repository.JpaRegisteredClientRepository;
import gov.med.security.web.service.JpaOAuth2AuthorizationService;
import gov.med.security.web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.authentication.ClientSecretAuthenticationProvider;


@RequiredArgsConstructor
@Configuration
public class SecurityComponent {
    private final UserDetailServiceConfig userDetailServiceConfig;
    private final JpaOAuth2AuthorizationService jpaOAuth2AuthorizationService;
    private final JpaRegisteredClientRepository jpaRegisteredClientRepository;

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationProvider authenticationProvider,
            ClientSecretAuthenticationProvider oAuth2ClientAuthentication
    ) {
        return new ProviderManager(
                authenticationProvider,
                oAuth2ClientAuthentication
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailServiceConfig);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public ClientSecretAuthenticationProvider clientSecretProvider() {
        ClientSecretAuthenticationProvider provider = new ClientSecretAuthenticationProvider(
                jpaRegisteredClientRepository, jpaOAuth2AuthorizationService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
}
