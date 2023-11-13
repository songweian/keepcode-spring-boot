package org.opengear.configdal.datasource;

public interface ConfigdalDatasource {

    Object get(String key);

    ConfigdalContent getContent(String appId, String environment, String cluster, String group);

    default void init() {
    }

}
