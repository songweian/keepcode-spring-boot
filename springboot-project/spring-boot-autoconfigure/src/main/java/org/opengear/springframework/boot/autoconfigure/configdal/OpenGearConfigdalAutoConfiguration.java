package org.opengear.springframework.boot.autoconfigure.configdal;

import org.opengear.configdal.client.OpenGearConfigClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

import java.util.Properties;

@ConditionalOnClass(OpenGearConfigClient.class)
public class OpenGearConfigdalAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public OpenGearConfigClient configdalClient(ConfigdalProperties properties) {
        Properties props = new Properties();
        props.setProperty("clientClass", "org.opengear.configdal.datasource.nacos.NacosClientFactory");
        return new OpenGearConfigClient(props);
    }

    @Bean
    @ConditionalOnMissingBean
    public ConfigdalProperties configdalProperties() {
        return new ConfigdalProperties();
    }

}
