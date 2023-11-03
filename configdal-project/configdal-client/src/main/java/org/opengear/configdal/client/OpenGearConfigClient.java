package org.opengear.configdal.client;

import org.opengear.configdal.datasource.ConfigdalDatasource;
import org.opengear.configdal.datasource.ConfigdalDatasourceFactory;

import java.util.List;
import java.util.Properties;

public class OpenGearConfigClient {

    private Properties properties;

    public OpenGearConfigClient(Properties properties) {
        this.properties = properties;
    }

    public List<ConfigdalDatasource> getConfigdalDatasources() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        String driverClass = properties.getProperty("clientClass", "");
        Class<?> aClass = Class.forName(driverClass);
        Object o = aClass.newInstance();
        ConfigdalDatasourceFactory factory = (ConfigdalDatasourceFactory) o;
        ConfigdalDatasource datasource = factory.getDatasource(properties);
        return List.of(datasource);
    }

    public CompositedConfigDalDatasource getCompositedConfigDalDatasource() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        List<ConfigdalDatasource> datasources = getConfigdalDatasources();
        return new CompositedConfigDalDatasource(datasources);
    }


}
