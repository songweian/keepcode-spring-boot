package org.opengear.configdal.datasource.nacos;

import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.annotation.NacosProperty;
import com.alibaba.nacos.api.exception.NacosException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opengear.configdal.datasource.ConfigDalCofigContent;
import org.opengear.configdal.datasource.ConfigdalDatasource;
import org.opengear.configdal.support.ConfigdalConfigUtils;
import org.opengear.configdal.support.ConfigdalExceptionUtils;

import java.util.Properties;

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
        String content = getContent("sdfsdf", "DEFAULT_GROUP");
        System.out.println(content);
        Properties properties = ConfigdalConfigUtils.fromJson(content);
        return properties.get(key);
    }

    @Override
    public String getContent(String appId, String environment, String cluster, String group) {
        String content = getContent("sdfsdf", "DEFAULT_GROUP");
        return content;
    }
}
