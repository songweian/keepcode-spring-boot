package org.opengear.configdal.support;

import org.json.JSONObject;

import java.util.Iterator;
import java.util.Properties;

public class ConfigdalConfigUtils {
    private ConfigdalConfigUtils() {
    }

    public static Properties fromJson(String content) {
        return jsonToProperties(content, null);
    }

    public static Properties jsonToProperties(String jsonContent, String parentKey) {
        JSONObject jsonObject = new JSONObject(jsonContent);
        Properties properties = new Properties();
        Iterator<String> keys = jsonObject.keys();

        while (keys.hasNext()) {
            String key = keys.next();
            String compoundKey = (parentKey != null) ? parentKey + "." + key : key;
            Object value = jsonObject.get(key);
            if (value instanceof JSONObject) {
                properties.putAll(jsonToProperties(value.toString(), compoundKey));
            } else {
                properties.setProperty(compoundKey, value.toString());
            }
        }
        return properties;
    }

}
