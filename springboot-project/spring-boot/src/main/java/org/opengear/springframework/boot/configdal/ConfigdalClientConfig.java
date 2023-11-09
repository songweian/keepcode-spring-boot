package org.opengear.springframework.boot.configdal;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;
import java.util.Properties;

@Data
@ConfigurationProperties(prefix = "opengear.configdal")
public class ConfigdalClientConfig {

    private String clientType;

    private NacosProperties nacos;

    private ApolloProperties apollo;

    private SpringCloudConfigProperties springCloudConfig;

    public Properties toProperties() {
        Properties properties = new Properties();
        properties.put("clientType", clientType);
        if (nacos != null) {
            properties.put("nacos.serverAddr", nacos.getServerAddr());
            properties.putAll(nacos.toProperties());
        }
        return properties;
    }

    @Data
    public static class NacosProperties {

        private String serverAddr;

        private Map<String, Object> configuration;

        public Properties toProperties() {
            Properties properties = new Properties();
            if (configuration != null) {
                properties.putAll(configuration);
            }
            return properties;
        }

    }

    @Data
    public static class ApolloProperties {
        private Map<String, Object> configuration;
    }

    @Data
    public static class SpringCloudConfigProperties {
        private Map<String, Object> configuration;
    }

}
