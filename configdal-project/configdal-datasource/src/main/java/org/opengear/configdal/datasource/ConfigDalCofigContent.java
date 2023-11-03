package org.opengear.configdal.datasource;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ConfigDalCofigContent {

    private final String content;
    private List<String> changedKeys;

    public Object get(String key) {
        return null;
    }
}
