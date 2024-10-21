package gov.med.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.firewall.DefaultHttpFirewall;

@Configuration
public class WebSecurity {
    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
    return (web) -> web.httpFirewall(new DefaultHttpFirewall());
  } 
}
