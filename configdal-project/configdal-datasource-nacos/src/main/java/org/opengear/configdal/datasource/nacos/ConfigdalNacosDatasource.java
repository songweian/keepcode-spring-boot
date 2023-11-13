package org.opengear.configdal.datasource.nacos;

import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import lombok.extern.slf4j.Slf4j;
import org.opengear.configdal.datasource.*;

import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Slf4j
public class ConfigdalNacosDatasource implements ConfigdalDatasource {
    private final String datasourceId = UUID.randomUUID().toString();
    private final ConfigService nacosConfigService;
    private final String nacosDataId;
    private final String nacosGroup;
    private final ConfigdalContent content = new BaseConfigdalContent();
    private final ConfigdalNotifier configdalNotifier;

    public ConfigdalNacosDatasource(ConfigService nacosConfigService, String nacosDataId, String nacosGroup, ConfigdalNotifier configdalNotifier) {
        this.nacosConfigService = nacosConfigService;
        this.nacosDataId = nacosDataId;
        this.nacosGroup = nacosGroup;
        this.configdalNotifier = configdalNotifier;
        this.init();
    }

    @Override
    public void init() {
        String content = getNacosContent();
        this.content.updateWithJson(content);
        try {
            nacosConfigService.addListener(nacosDataId, nacosGroup, new NacosConfigdalListenerProxy(this.configdalNotifier));
        } catch (NacosException e) {
            throw new RuntimeException(e);
        }
        configdalNotifier.registListener(new NacosConfigdalListener());
    }

    protected String getNacosContent() {
        try {
            return nacosConfigService.getConfig(nacosDataId, nacosGroup, 30000);
        } catch (NacosException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object get(String key) {
        return this.content.get(key);
    }

    @Override
    public ConfigdalContent getContent(String appId, String environment, String cluster, String group) {
        String dataId = NacosSupport.dataId(appId, environment, cluster, group);
        return this.content;
    }

    protected void onContentChanged(String changedContent) {
        this.content.updateWithJson(changedContent);
    }

    public class NacosConfigdalListenerProxy implements Listener {
        private final ConfigdalNotifier configdalNotifier;

        public NacosConfigdalListenerProxy(ConfigdalNotifier configdalNotifier) {
            this.configdalNotifier = configdalNotifier;
        }

        @Override
        public Executor getExecutor() {
            return Executors.newSingleThreadExecutor();
        }

        @Override
        public void receiveConfigInfo(String configInfo) {
            configdalNotifier.notifyConfigChanged(datasourceId, configInfo);
        }
    }

    public class NacosConfigdalListener implements ConfigdalListener {

        @Override
        public void onConfigChanged(String datasourceId, String changedContent) {
            ConfigdalNacosDatasource.this.onContentChanged(changedContent);
        }
    }

}
