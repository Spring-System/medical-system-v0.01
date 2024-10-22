package gov.med.security.web.core;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "`client`")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String clientId;
    private String clientSecret;
    @Column(length = 1000)
    private String authorizationGrantTypes;
    @Column(length = 1000)
    private String redirectUris;
    @Column(length = 1000)
    private String scopes;
    @Column(length = 2000)
    private String clientSettings;
    private String type;
    @Column(length = 2000)
    private String tokenSettings;
    private String otpSetting;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
