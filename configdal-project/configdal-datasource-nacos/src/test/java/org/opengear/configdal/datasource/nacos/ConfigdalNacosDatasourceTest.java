package org.opengear.configdal.datasource.nacos;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;

import java.util.Properties;
import java.util.concurrent.Executor;

class ConfigdalNacosDatasourceTest {
    private static final String serverAddr = "g7n-qce-nacos.test.chinawayltd.com:30849";
    private static final String dataId = "dfp-cloud-idispatch-push-test.yml";
    private static final String group = "DEFAULT_GROUP";

    @org.junit.jupiter.api.Test
    void getContent() throws NacosException {
        // Initialize the configuration service, and the console automatically obtains the following parameters through the sample code.
//        String serverAddr = "{serverAddr}";
//        String dataId = "{dataId}";
//        String group = "{group}";
        Properties properties = new Properties();
        properties.put("serverAddr", serverAddr);
        ConfigService configService = NacosFactory.createConfigService(properties);
        // Actively get the configuration.
        String content = configService.getConfig(dataId, group, 5000);
        System.out.println(content);
    }

    public static void main(String[] args) throws NacosException, InterruptedException {
        String dataId = "dfp-cloud-idispatch-push-dev.yml";
        String group = "DEFAULT_GROUP";
        Properties props = new Properties();
//        props.put(PropertyKeyConst.SERVER_ADDR, serverAddr);
//        props.setProperty("secretKey", "");
        props.setProperty("namespace", "csc-cloud-prod");
//        props.setProperty("ramRoleName", "");
//        props.setProperty("username", "");
//        props.setProperty("enableRemoteSyncConfig", "false");
//        props.setProperty("configLongPollTimeout", "");
//        props.setProperty("configRetryTime", "");
//        props.setProperty("encode", "");
        props.setProperty("serverAddr", "g7n-qce-nacos.test.chinawayltd.com:30849");
//        props.setProperty("maxRetry", "");
//        props.setProperty("clusterName", "");
//        props.setProperty("password", "");
//        props.setProperty("accessKey", "");
//        props.setProperty("endpoint", "");

        ConfigService configService = NacosFactory.createConfigService(props);
        String content = configService.getConfig(dataId, group, 50000);
        System.out.println(content);
        configService.addListener(dataId, group, new Listener() {
            @Override
            public void receiveConfigInfo(String configInfo) {
                System.out.println("recieve:" + configInfo);
            }

            @Override
            public Executor getExecutor() {
                return null;
            }
        });

//        boolean isPublishOk = configService.publishConfig(dataId, group, "content");
//        System.out.println(isPublishOk);
//
//        Thread.sleep(3000);
//        content = configService.getConfig(dataId, group, 5000);
//        System.out.println(content);
//
//        boolean isRemoveOk = configService.removeConfig(dataId, group);
//        System.out.println(isRemoveOk);
//        Thread.sleep(3000);
//
//        content = configService.getConfig(dataId, group, 5000);
//        System.out.println(content);
//        Thread.sleep(300000);

    }

}