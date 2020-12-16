package com.simek.offero.infrastructure.security.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
@Getter
@Setter
public class JWTProperties {
    private String secret;
    private Long expTime;
    private String tokenPrefix;
    private String authHeaderName;
    private String signUpURL;
}
