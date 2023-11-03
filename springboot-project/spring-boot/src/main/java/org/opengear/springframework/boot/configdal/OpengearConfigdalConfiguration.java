package org.opengear.springframework.boot.configdal;

import org.opengear.configdal.client.CompositedConfigDalDatasource;
import org.opengear.configdal.client.OpenGearConfigClient;
import org.opengear.configdal.datasource.ConfigdalDatasource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.PropertySource;

import java.util.List;
import java.util.Properties;

@Configuration
@Import({ConfigdalNacosProperties.class})
public class OpengearConfigdalConfiguration {

    @Bean
    public ConfigdalDatasource configdalDatasource(ConfigdalNacosProperties properties) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Properties props = new Properties();
        OpenGearConfigClient client = new OpenGearConfigClient(props);
        CompositedConfigDalDatasource configdalDatasources = client.getCompositedConfigDalDatasource();
        return configdalDatasources;
    }


    @Bean
    public PropertySource configdalPropertySource(ConfigdalDatasource configdalDatasource) {
        return new ConfigdalPropertySource("configdal", configdalDatasource);
    }


}
