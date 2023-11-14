package org.opengear.configdal.datasource;

import org.opengear.configdal.support.ConfigdalConfigUtils;

import java.util.*;

public class BaseConfigdalContent implements ConfigdalContent {
    protected final Map<String, Object> currentValueMap = new HashMap<>();
    protected final Set<String> changedKeys = new HashSet<>();
    protected final Map<String, Object> oldValueMap = new HashMap<>();

    @Override
    public void set(String key, String value) {
        Object put = currentValueMap.put(key, value);
    }

    @Override
    public void updateWithJson(String content) {
        synchronized (this.changedKeys) {
            Properties properties = ConfigdalConfigUtils.fromJson(content);
            properties.forEach((k, v) -> {
                String key = k.toString();
                Object oldValue = this.currentValueMap.put(key, v);
                if (this.changedKeys.contains(key)) {
                    return;
                }
                // add | update
                this.changedKeys.add(key);
                this.oldValueMap.put(key, oldValue);
            });
            // delete

            Set<String> deletedKeys = new HashSet<>();
            for (String key : this.currentValueMap.keySet()) {
                if (properties.containsKey(key)) {
                    continue;
                }
                deletedKeys.add(key);
                Object oldValue = this.currentValueMap.get(key);
                this.currentValueMap.remove(key);
                if (!this.changedKeys.contains(key)) {
                    this.changedKeys.add(key);
                    this.oldValueMap.put(key, oldValue);
                }
            }
            for (String delKey : deletedKeys) {
                this.currentValueMap.remove(delKey);
            }
        }
    }

    @Override
    public void updateWithText(String content) {
        synchronized (this.changedKeys) {

        }
    }

    @Override
    public void updateWithYaml(String content) {
        synchronized (this.changedKeys) {

        }
    }

    @Override
    public void updateWithProperties(String content) {
        synchronized (this.changedKeys) {

        }
    }

    @Override
    public void updateWithXml(String content) {
        synchronized (this.changedKeys) {

        }
    }

    @Override
    public void updateWithHtml(String html) {
        synchronized (this.changedKeys) {

        }
    }

    @Override
    public Set<String> changedKeys() {
        return this.changedKeys;
    }

    @Override
    public boolean changed() {
        return !this.changedKeys.isEmpty();
    }

    @Override
    public void clearChangeContext() {
        synchronized (this.changedKeys) {
            this.changedKeys.clear();
            this.oldValueMap.clear();
        }
    }

    @Override
    public Object get(String key) {
        return this.currentValueMap.get(key);
    }
}
