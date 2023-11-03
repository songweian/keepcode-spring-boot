package org.opengear.configdal.datasource;

public interface ConfigdalDatasource {

    String getContent(String dataId, String group);

    Object get(String key);
}
