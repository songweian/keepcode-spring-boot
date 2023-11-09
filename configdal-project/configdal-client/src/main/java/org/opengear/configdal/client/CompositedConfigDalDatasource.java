package org.opengear.configdal.client;

import lombok.RequiredArgsConstructor;
import org.opengear.configdal.datasource.ConfigdalDatasource;

import java.util.List;

@RequiredArgsConstructor
public class CompositedConfigDalDatasource implements ConfigdalDatasource {
    private final List<ConfigdalDatasource> datasources;

    public Object get(String key) {
        for (ConfigdalDatasource datasource : datasources) {
            Object value = datasource.get(key);
            if (value != null) {
                return value;
            }
        }
        return null;
    }

    @Override
    public String getContent(String appId, String environment, String cluster, String group) {
        return null;
    }

    @Override
    public String getContent(String dataId, String group) {
        return null;
    }
}
