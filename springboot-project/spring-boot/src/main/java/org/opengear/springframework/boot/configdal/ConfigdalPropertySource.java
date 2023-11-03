package org.opengear.springframework.boot.configdal;

import org.opengear.configdal.datasource.ConfigdalDatasource;
import org.springframework.core.env.PropertySource;

public class ConfigdalPropertySource extends PropertySource<ConfigdalDatasource> {

    public ConfigdalPropertySource(String name, ConfigdalDatasource source) {
        super(name, source);
    }

    @Override
    public Object getProperty(String name) {
        return source.get(name);
    }

}
