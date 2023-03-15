package by.intervale.adapter.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "response")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class PropertyResponse {
    private String uri;
    private int maxAttempts;
    private int delayAttempts;
}
