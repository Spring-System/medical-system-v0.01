package gov.med.security.config;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.introspect.DefaultAccessorNamingStrategy.Provider;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;
import java.util.TimeZone;


@Configuration
@AllArgsConstructor
public class AppConfig {
    @Bean
    public LocaleResolver localeResolver(@Value("${app.default-locale:vi}") final String defaultLocale,
                                         @Value("${app.default-timezone:Asia/Ho_Chi_Minh}") final String defaultTimezone) {
        AcceptHeaderLocaleResolver localResolver = new AcceptHeaderLocaleResolver();
        localResolver.setDefaultLocale(new Locale.Builder().setLanguage(defaultLocale).build());

        TimeZone.setDefault(TimeZone.getTimeZone(defaultTimezone));

        return localResolver;
    }

    @Bean
    public ObjectMapper objectMapper() {
    ObjectMapper objectMapper = JsonMapper.builder()
            .accessorNaming(new Provider().withBuilderPrefix(""))
            .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .serializationInclusion(Include.NON_NULL)
            .build();

      objectMapper.registerModule(new JavaTimeModule());
      return objectMapper;
    }

    @Bean
    MappingJackson2HttpMessageConverter jackson2HttpMessageConverter(ObjectMapper objectMapper) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper);
        return converter;
    }
}
