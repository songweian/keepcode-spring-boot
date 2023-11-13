package org.opengear.configdal.client;

import org.opengear.configdal.datasource.ConfigdalDatasource;
import org.opengear.configdal.datasource.ConfigdalListener;
import org.opengear.configdal.datasource.ConfigdalNotifier;

import java.util.List;
import java.util.Properties;

public class OpenGearConfigdalClient {

    private final ConfigdalNotifier configdalNotifier = new ConfigdalNotifier();
    private Properties properties;

    public OpenGearConfigdalClient(Properties properties) {
        this.properties = properties;
    }

    protected List<ConfigdalDatasource> getConfigdalDatasourceList() {
        ConfigdalDatasource datasource = createDatasource(properties);
        return List.of(datasource);
    }

    protected ConfigdalDatasource createDatasource(Properties properties) {
        try {
            CompositedConfigdalDatasourceFactory factory = new CompositedConfigdalDatasourceFactory(configdalNotifier);
            ConfigdalDatasource datasource = factory.getDatasource(properties);
            return datasource;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public CompositedConfigDalDatasource getConfigdalDatasource() {
        return new CompositedConfigDalDatasource(getConfigdalDatasourceList());
    }

    public void addListener(ConfigdalListener configdalListener) {
        configdalNotifier.registListener(configdalListener);
    }
}
