package org.opengear.configdal.examples.nacos;

import org.opengear.configdal.client.CompositedConfigDalDatasource;
import org.opengear.configdal.client.OpenGearConfigdalClient;
import org.opengear.configdal.datasource.ConfigdalListener;

import java.util.Properties;

public class NacosConfigdalDemo {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InterruptedException {
        String dataId = "";
        String group = "DEFAULT_GROUP";
        Properties nacosProperties = new Properties();
        nacosProperties.setProperty("clientType", "nacos");
        nacosProperties.put("appId", "configdal-demo-app");
        nacosProperties.put("env", "dev");
        nacosProperties.put("cluster", "beijing-01");
        nacosProperties.put("group", "kafka-client-01");
//        nacosProperties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);
//        nacosProperties.setProperty("secretKey", "");
//        nacosProperties.setProperty("namespace", "public");
//        nacosProperties.setProperty("ramRoleName", "");
//        nacosProperties.setProperty("username", "");
//        nacosProperties.setProperty("enableRemoteSyncConfig", "false");
//        nacosProperties.setProperty("configLongPollTimeout", "");
//        nacosProperties.setProperty("configRetryTime", "");
//        nacosProperties.setProperty("encode", "");
        nacosProperties.setProperty("nacos.serverAddr", "localhost");
//        nacosProperties.setProperty("maxRetry", "");
//        nacosProperties.setProperty("clusterName", "");
//        nacosProperties.setProperty("password", "");
//        nacosProperties.setProperty("accessKey", "");
//        nacosProperties.setProperty("endpoint", "");
//        props.put("nacos", nacosProperties);
        OpenGearConfigdalClient client = new OpenGearConfigdalClient(nacosProperties);
        CompositedConfigDalDatasource configdalDatasources = client.getConfigdalDatasource();
        Object content = configdalDatasources.get("person.name");
        System.out.println(content);
        client.addListener(new ConfigdalListener() {
            @Override
            public void onConfigChanged(String datasourceId, String changedContent) {
                System.out.println("config changed " + datasourceId + " " + changedContent);
            }
        });
        Thread.sleep(Integer.MAX_VALUE);
    }
}
