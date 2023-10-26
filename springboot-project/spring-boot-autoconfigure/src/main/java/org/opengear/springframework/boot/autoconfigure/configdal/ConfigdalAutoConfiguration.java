package org.opengear.springframework.boot.autoconfigure.configdal;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.opengear.configdal.client.ConfigdalClient;

@ConditionalOnClass(ConfigdalClient.class)
public class ConfigdalAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ConfigdalClient configdalClient(ConfigdalProperties properties) {
        return new ConfigdalClient();
    }

    @Bean
    @ConditionalOnMissingBean
    public ConfigdalProperties configdalProperties() {
        return new ConfigdalProperties();
    }

}
