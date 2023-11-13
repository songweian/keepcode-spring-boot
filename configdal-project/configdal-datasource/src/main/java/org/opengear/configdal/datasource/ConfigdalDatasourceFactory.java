package org.opengear.configdal.datasource;

import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public interface ConfigdalDatasourceFactory {

    ConfigdalDatasource getDatasource(Properties properties) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException;
}
