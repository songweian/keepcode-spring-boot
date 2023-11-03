package org.opengear.configdal.datasource.nacos;

import com.alibaba.nacos.api.config.ConfigFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import org.opengear.configdal.datasource.ConfigdalDatasource;
import org.opengear.configdal.datasource.ConfigdalDatasourceFactory;

import java.util.Properties;

public class ConfigdalNacosDatasourceFactory implements ConfigdalDatasourceFactory {

    @Override
    public ConfigdalDatasource getDatasource(Properties properties) {
        try {
            return new ConfigdalNacosDatasource(getNacosConfigService(properties));
        } catch (NacosException e) {
            throw new RuntimeException(e);
        }
    }

    public ConfigService getNacosConfigService(Properties properties) throws NacosException {
        ConfigService configService = ConfigFactory.createConfigService(properties);
        return configService;
    }

}
