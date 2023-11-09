package org.opengear.springframework.boot.configdal;

import org.opengear.configdal.datasource.ConfigdalDatasource;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class ConfigdalPropertySourceInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigdalDatasource configdalDatasource = (ConfigdalDatasource) applicationContext.getBean("configdalPropertySource");
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        // Populate the source map with your properties
        // For example, you can load them from a file, a database, etc.
        ConfigdalPropertySource propertySource = new ConfigdalPropertySource("configdalPropertySource", configdalDatasource);
        environment.getPropertySources().addFirst(propertySource);
    }
}
