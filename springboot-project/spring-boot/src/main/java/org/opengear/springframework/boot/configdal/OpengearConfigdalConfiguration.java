package org.opengear.springframework.boot.configdal;

import org.opengear.configdal.client.CompositedConfigDalDatasource;
import org.opengear.configdal.client.OpenGearConfigdalClient;
import org.opengear.configdal.datasource.ConfigdalDatasource;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

@Configuration
@EnableConfigurationProperties(ConfigdalProperties.class)
@ConditionalOnClass(OpenGearConfigdalClient.class)
public class OpengearConfigdalConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public OpenGearConfigdalClient openGearConfigdalClient(ConfigdalProperties properties) {
        return new OpenGearConfigdalClient(ConfigdalSupport.mapToProperties(properties.getConfigdal()));
    }

    @Bean
    @ConditionalOnMissingBean
    public ConfigdalDatasource configdalDatasource(OpenGearConfigdalClient configdalClient) {
        CompositedConfigDalDatasource configdalDatasource = configdalClient.getConfigdalDatasource();
        return configdalDatasource;
    }


    @Bean
    @Qualifier("configdalPropertySource")
    public PropertySource configdalPropertySource(ConfigdalDatasource configdalDatasource) {
        return new ConfigdalPropertySource("configdal", configdalDatasource);
    }

    @Bean
    public InitializingBean addPropertySource(Environment environment, @Qualifier("configdalPropertySource") PropertySource<?> myPropertySource) {
        return () -> {
            MutablePropertySources propertySources = ((ConfigurableEnvironment) environment).getPropertySources();
            propertySources.addFirst(myPropertySource);
        };
    }

}
