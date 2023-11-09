package org.opengear.springframework.boot.autoconfigure.configdal;

import org.opengear.configdal.client.OpenGearConfigdalClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

import java.util.Properties;

@ConditionalOnClass(OpenGearConfigdalClient.class)
public class OpenGearConfigdalAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public OpenGearConfigdalClient configdalClient(ConfigdalProperties properties) {
        Properties props = new Properties();
        props.setProperty("clientClass", "org.opengear.configdal.datasource.nacos.NacosClientFactory");
        return new OpenGearConfigdalClient(props);
    }

    @Bean
    @ConditionalOnMissingBean
    public ConfigdalProperties configdalProperties() {
        return new ConfigdalProperties();
    }

}
