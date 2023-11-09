package org.opengear.springframework.boot.configdal;

import java.util.Map;
import java.util.Properties;

public class ConfigdalSupport {


    public static Properties mapToProperties(Map<String, Object> map) {
        Properties properties = new Properties();
        mapToPropertiesInternal(null, map, properties);
        return properties;
    }

    private static void mapToPropertiesInternal(String keyPrefix, Map<String, Object> map, Properties properties) {
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
