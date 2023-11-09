package org.opengear.configdal.datasource;

public interface ConfigdalDatasource {

    String getContent(String appId, String group);

    Object get(String key);

    String getContent(String appId, String environment, String cluster, String group);

}
