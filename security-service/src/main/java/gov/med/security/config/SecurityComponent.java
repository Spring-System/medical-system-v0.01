package gov.med.security.config;

import gov.med.security.web.repository.JpaRegisteredClientRepository;
import gov.med.security.web.service.JpaOAuth2AuthorizationService;
import gov.med.security.web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.authentication.ClientSecretAuthenticationProvider;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeAuthenticationProvider;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;

import java.util.UUID;

@RequiredArgsConstructor
public class SecurityComponent {
    private final UserService userService;
    private final JpaOAuth2AuthorizationService jpaOAuth2AuthorizationService;
    private final JpaRegisteredClientRepository jpaRegisteredClientRepository;

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationProvider authenticationProvider,
            OAuth2AuthorizationCodeAuthenticationProvider oAuth2AuthorizationCodeAuthenticationProvider,
            ClientSecretAuthenticationProvider oAuth2ClientAuthentication
    ) {
        return new ProviderManager(
                authenticationProvider,
                oAuth2AuthorizationCodeAuthenticationProvider,
                oAuth2ClientAuthentication
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public String test() {
        RegisteredClient oidcClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("client1")
                .clientSecret(passwordEncoder().encode("123"))
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .redirectUri("http://localhost:8081/login/oauth2/callback/my-client")
                .redirectUri("https://oauth.pstmn.io/v1/callback")
                .postLogoutRedirectUri("http://localhost:8080/")
                .scope(OidcScopes.OPENID)
                .scope(OidcScopes.PROFILE)
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(false).build())
                .build();

        jpaRegisteredClientRepository.save(oidcClient);
        return "ADDED";
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return email -> userService.findByUsername(email)
                .orElseThrow(() -> new UsernameNotFoundException("Users not found with email" + email));
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
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
