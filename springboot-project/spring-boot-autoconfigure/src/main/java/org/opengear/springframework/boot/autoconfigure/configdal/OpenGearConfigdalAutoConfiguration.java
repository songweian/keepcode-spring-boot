package org.opengear.springframework.boot.autoconfigure.configdal;

import org.opengear.configdal.client.OpenGearConfigClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@ConditionalOnClass(OpenGearConfigClient.class)
public class OpenGearConfigdalAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public OpenGearConfigClient configdalClient(ConfigdalProperties properties) {
        return new OpenGearConfigClient();
    }

    @Bean
    @ConditionalOnMissingBean
    public ConfigdalProperties configdalProperties() {
        return new ConfigdalProperties();
    }

}
