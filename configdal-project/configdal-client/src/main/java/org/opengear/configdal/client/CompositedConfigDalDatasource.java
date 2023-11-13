package org.opengear.configdal.client;

import org.opengear.configdal.datasource.ConfigdalContent;
import org.opengear.configdal.datasource.ConfigdalDatasource;

import java.util.List;

public class CompositedConfigDalDatasource implements ConfigdalDatasource {
    private final List<ConfigdalDatasource> datasources;

    public CompositedConfigDalDatasource(List<ConfigdalDatasource> datasources) {
        this.datasources = datasources;
    }

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
    public ConfigdalContent getContent(String appId, String environment, String cluster, String group) {
        for (ConfigdalDatasource datasource : datasources) {
            ConfigdalContent value = datasource.getContent(appId, environment, cluster, group);
            if (value != null) {
                return value;
            }
        }
        return null;
    }

}
