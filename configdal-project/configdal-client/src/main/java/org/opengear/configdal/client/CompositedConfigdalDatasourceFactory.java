package org.opengear.configdal.client;

import org.opengear.configdal.datasource.ConfigdalDatasource;
import org.opengear.configdal.datasource.ConfigdalDatasourceFactory;
import org.opengear.configdal.support.ConfigdalExceptionUtils;

import java.util.Properties;

import static org.opengear.configdal.client.ClientType.NACOS;

public class CompositedConfigdalDatasourceFactory implements org.opengear.configdal.datasource.ConfigdalDatasourceFactory {

    @Override
    public ConfigdalDatasource getDatasource(Properties properties) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        ClientType clientType = ClientType.val(properties.getProperty("clientType"));
        if (clientType == null) {
            throw ConfigdalExceptionUtils.clientError("clientType can not be null");
        }
        switch (clientType) {
            case NACOS:
                Properties nacosProps = new Properties();
                properties.forEach((k, v) -> {
                    if (k.toString().startsWith(NACOS.getConfigPrefix())) {
                        nacosProps.put(k.toString().substring(NACOS.getConfigPrefix().length() + 1), v);
                    }
                });
                return createNacosDatasource(nacosProps);
            default:
                throw ConfigdalExceptionUtils.clientError("clientType not supported");
        }
    }

    private ConfigdalDatasource createNacosDatasource(Properties nacosProperties) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        ConfigdalDatasourceFactory factory = newInstance();
        ConfigdalDatasource datasource = factory.getDatasource(nacosProperties);
        return datasource;
    }

    private static ConfigdalDatasourceFactory newInstance() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        String datasourceFactory = "org.opengear.configdal.datasource.nacos.ConfigdalNacosDatasourceFactory";
        Class<?> factoryClass = Class.forName(datasourceFactory);
        return (ConfigdalDatasourceFactory) factoryClass.newInstance();
    }

}
