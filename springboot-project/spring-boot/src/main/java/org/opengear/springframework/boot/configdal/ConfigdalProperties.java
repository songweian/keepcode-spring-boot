package org.opengear.springframework.boot.configdal;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@Data
@ConfigurationProperties(prefix = "opengear")
public class ConfigdalProperties {
    private Map<String, Object> configdal;
}
