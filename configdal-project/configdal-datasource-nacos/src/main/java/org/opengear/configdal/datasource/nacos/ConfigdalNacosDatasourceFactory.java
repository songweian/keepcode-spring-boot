package org.opengear.configdal.datasource.nacos;

import com.alibaba.nacos.api.config.ConfigFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import org.opengear.configdal.datasource.ConfigdalDatasource;
import org.opengear.configdal.datasource.ConfigdalDatasourceFactory;
import org.opengear.configdal.datasource.ConfigdalNotifier;

import java.util.Properties;

public class ConfigdalNacosDatasourceFactory implements ConfigdalDatasourceFactory {
    private final ConfigdalNotifier configdalNotifier;

    public ConfigdalNacosDatasourceFactory(ConfigdalNotifier configdalNotifier) {
        this.configdalNotifier = configdalNotifier;
    }

    @Override
    public ConfigdalDatasource getDatasource(Properties properties) {
        try {
            Properties nacosProps = new Properties();
            properties.forEach((k, v) -> {
                if (k.toString().startsWith("nacos")) {
                    nacosProps.put(k.toString().substring("nacos".length() + 1), v);
                }
            });
            String appId = properties.getProperty("appId", "");
            String env = properties.getProperty("env", "");
            String cluster = properties.getProperty("cluster", "");
            String group = properties.getProperty("group", "");
            String dataId = NacosSupport.dataId(appId, env, cluster, group);
            return new ConfigdalNacosDatasource(getNacosConfigService(nacosProps), dataId, "DEFAULT_GROUP", configdalNotifier);
        } catch (NacosException e) {
            throw new RuntimeException(e);
        }
    }

    public ConfigService getNacosConfigService(Properties properties) throws NacosException {
        ConfigService configService = ConfigFactory.createConfigService(properties);
        return configService;
    }

}
