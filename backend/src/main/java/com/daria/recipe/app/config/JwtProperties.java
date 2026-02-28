package com.daria.recipe.app.config;

import lombok.Builder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Data
@Component
@ConfigurationProperties(prefix = "jwt")
@Builder
public class JwtProperties {
    private String secret;

    @Builder.Default
    private Duration expiration = Duration.ofHours(24);

    public long getExpiration() {
        return expiration.toMillis();
    }
}
