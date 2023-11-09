package org.opengear.configdal.examples.nacos;

import org.opengear.configdal.client.CompositedConfigDalDatasource;
import org.opengear.configdal.client.OpenGearConfigdalClient;

import java.util.Properties;

public class NacosConfigdalDemo {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        String dataId = "";
        String group = "DEFAULT_GROUP";
        Properties props = new Properties();
        props.setProperty("clientType", "nacos");
        Properties nacosProperties = new Properties();
//        nacosProperties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);
//        nacosProperties.setProperty("secretKey", "");
//        nacosProperties.setProperty("namespace", "public");
//        nacosProperties.setProperty("ramRoleName", "");
//        nacosProperties.setProperty("username", "");
//        nacosProperties.setProperty("enableRemoteSyncConfig", "false");
//        nacosProperties.setProperty("configLongPollTimeout", "");
//        nacosProperties.setProperty("configRetryTime", "");
//        nacosProperties.setProperty("encode", "");
        nacosProperties.setProperty("serverAddr", "localhost");
//        nacosProperties.setProperty("maxRetry", "");
//        nacosProperties.setProperty("clusterName", "");
//        nacosProperties.setProperty("password", "");
//        nacosProperties.setProperty("accessKey", "");
//        nacosProperties.setProperty("endpoint", "");
        props.put("nacosProperties", nacosProperties);
        OpenGearConfigdalClient client = new OpenGearConfigdalClient(props);
        CompositedConfigDalDatasource configdalDatasources = client.getConfigdalDatasource();
        Object content = configdalDatasources.get("person.name");
        System.out.println(content);
    }
}
