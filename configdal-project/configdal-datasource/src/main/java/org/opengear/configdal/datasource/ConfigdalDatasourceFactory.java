package org.opengear.configdal.datasource;

import java.util.Properties;

public interface ConfigdalDatasourceFactory {

    ConfigdalDatasource getDatasource(Properties properties);
}
