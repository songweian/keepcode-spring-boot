package org.opengear.springframework.boot.configdal;

import org.opengear.configdal.client.CompositedConfigDalDatasource;
import org.opengear.configdal.client.OpenGearConfigdalClient;
import org.opengear.configdal.datasource.ConfigdalDatasource;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import java.util.Map;
import java.util.Properties;

@Configuration
@EnableConfigurationProperties(ConfigdalProperties.class)
public class OpengearConfigdalConfiguration {

//    @Bean
//
//    public ConfigdalProperties configdalProperties() {
//        return new ConfigdalProperties();
//    }

    @Bean
    public ConfigdalDatasource configdalDatasource(ConfigdalProperties properties) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Map<String, Object> configdalProps = properties.getConfigdal();
        Properties props = mapToProperties(configdalProps);
        OpenGearConfigdalClient client = new OpenGearConfigdalClient(props);
        CompositedConfigDalDatasource configdalDatasource = client.getConfigdalDatasource();
        return configdalDatasource;
    }

    public Properties mapToProperties(Map<String, Object> map) {
        Properties properties = new Properties();
        mapToPropertiesInternal(null, map, properties);
        return properties;
    }

    private void mapToPropertiesInternal(String keyPrefix, Map<String, Object> map, Properties properties) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = keyPrefix != null ? keyPrefix + "." + entry.getKey() : entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Map) {
                // If the value is a map, we need to dive deeper
                mapToPropertiesInternal(key, (Map<String, Object>) value, properties);
            } else {
                // If the value is not a map, we can add it to the properties
                properties.put(key, value.toString());
            }
        }
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

//    @Bean
//    public PropertySourceLoader configdalPropertySourceLoader() {
//        return new ConfigdalPropertySourceLocator();
//    }

}
