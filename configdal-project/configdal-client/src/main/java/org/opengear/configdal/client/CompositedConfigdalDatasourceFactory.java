package org.opengear.configdal.client;

import org.opengear.configdal.datasource.ConfigdalDatasource;
import org.opengear.configdal.datasource.ConfigdalDatasourceFactory;
import org.opengear.configdal.datasource.ConfigdalNotifier;
import org.opengear.configdal.support.ConfigdalExceptionUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class CompositedConfigdalDatasourceFactory implements org.opengear.configdal.datasource.ConfigdalDatasourceFactory {
    private final ConfigdalNotifier configdalNotifier;

    public CompositedConfigdalDatasourceFactory(ConfigdalNotifier configdalNotifier) {
        this.configdalNotifier = configdalNotifier;
    }

    @Override
    public ConfigdalDatasource getDatasource(Properties properties) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        ClientType clientType = ClientType.val(properties.getProperty("clientType"));
        if (clientType == null) {
            throw ConfigdalExceptionUtils.clientError("clientType can not be null");
        }
        switch (clientType) {
            case NACOS:
                return createNacosDatasource(properties);
            default:
                throw ConfigdalExceptionUtils.clientError("clientType not supported");
        }
    }

    private ConfigdalDatasource createNacosDatasource(Properties nacosProperties) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        ConfigdalDatasourceFactory factory = newInstance();
        ConfigdalDatasource datasource = factory.getDatasource(nacosProperties);
        return datasource;
    }

    private ConfigdalDatasourceFactory newInstance() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        String datasourceFactory = "org.opengear.configdal.datasource.nacos.ConfigdalNacosDatasourceFactory";
        Class<?> factoryClass = Class.forName(datasourceFactory);
        Constructor<?> constructor = factoryClass.getConstructor(ConfigdalNotifier.class);
        if (constructor == null) {
            throw new AbstractMethodError("");
        }
        Object factoryInstance = constructor.newInstance(configdalNotifier);
        return (ConfigdalDatasourceFactory) factoryInstance;
    }

}
