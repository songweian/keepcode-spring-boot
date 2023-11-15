package org.opengear.configdal.datasource.apollo;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import org.opengear.configdal.datasource.ConfigdalContent;
import org.opengear.configdal.datasource.ConfigdalDatasource;

public class ConfigdalApolloDatasource implements ConfigdalDatasource {

    @Override
    public Object get(String key) {
        Config config = ConfigService.getAppConfig();
        String someKey = "someKey";
        String defaultValue = "defaultValue";
        config.addChangeListener(new ConfigChangeListener() {
            @Override
            public void onChange(ConfigChangeEvent configChangeEvent) {

            }
        });

        String value = config.getProperty(someKey, defaultValue);
        System.out.println(value);
        return null;
    }

    @Override
    public ConfigdalContent getContent(String appId, String environment, String cluster, String group) {
        return null;
    }
}
