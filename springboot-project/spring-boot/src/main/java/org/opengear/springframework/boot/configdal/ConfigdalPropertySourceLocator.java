package org.opengear.springframework.boot.configdal;

import lombok.RequiredArgsConstructor;
import org.opengear.configdal.client.CompositedConfigDalDatasource;
import org.opengear.configdal.client.OpenGearConfigdalClient;
import org.opengear.configdal.datasource.ConfigdalDatasource;
import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Order(0)
@RequiredArgsConstructor
public class ConfigdalPropertySourceLocator implements PropertySourceLoader {

    private final ConfigdalProperties configdalProperties;

    @Override
    public String[] getFileExtensions() {
        return new String[0];
    }

    @Override
    public List<PropertySource<?>> load(String name, Resource resource) throws IOException {
        return null;
    }


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

}
