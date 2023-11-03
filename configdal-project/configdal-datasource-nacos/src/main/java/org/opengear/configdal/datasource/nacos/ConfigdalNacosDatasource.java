package org.opengear.configdal.datasource.nacos;

import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opengear.configdal.datasource.ConfigDalCofigContent;
import org.opengear.configdal.datasource.ConfigdalDatasource;
import org.opengear.configdal.support.ConfigdalExceptionUtils;

@RequiredArgsConstructor
@Slf4j
public class ConfigdalNacosDatasource implements ConfigdalDatasource {

    private final ConfigService nacosConfigService;

    @Override
    public String getContent(String dataId, String group) {
        String config;
        try {
            config = nacosConfigService.getConfig(dataId, group, 1000);
        } catch (NacosException e) {
            throw ConfigdalExceptionUtils.wrapException(e);
        }
        return config;
    }

    @Override
    public Object get(String key) {
        String content = getContent("test", "test");
        log.info("content: {}", content);
        ConfigDalCofigContent configDalCofigContent = new ConfigDalCofigContent(content);
        return configDalCofigContent.get(key);
    }
}
