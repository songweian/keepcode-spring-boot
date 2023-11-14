package org.opengear.configdal.datasource;

import java.util.Properties;
import java.util.Set;

public interface ConfigdalContent {

    static ConfigdalContent fromJson(String content) {
        return null;
    }

    static ConfigdalContent fromYaml(String content) {
        return null;
    }

    static ConfigdalContent fromProperties(Properties properties) {
        return null;
    }

    static ConfigdalContent fromText(String text) {
        return null;
    }

    static ConfigdalContent fromXml(String content) {
        return null;
    }

    static ConfigdalContent fromHtml(String html) {
        return null;
    }

    void set(String key, String value);

    void updateWithJson(String content);

    void updateWithText(String content);

    void updateWithYaml(String content);

    void updateWithProperties(String content);

    void updateWithXml(String content);

    void updateWithHtml(String html);

    Set<String> changedKeys();

    boolean changed();

    void clearChangeContext();

    Object get(String key);
}
