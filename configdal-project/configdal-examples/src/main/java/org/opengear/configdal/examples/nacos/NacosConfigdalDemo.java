package org.opengear.configdal.examples.nacos;

import org.opengear.configdal.client.CompositedConfigDalDatasource;
import org.opengear.configdal.client.OpenGearConfigClient;
import org.opengear.configdal.datasource.nacos.ConfigdalNacosDatasourceFactory;

import java.util.Properties;

public class NacosConfigdalDemo {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        String dataId = "";
        String group = "DEFAULT_GROUP";
        Properties props = new Properties();
        ConfigdalNacosDatasourceFactory factory = null;
        props.setProperty("clientClass", "org.opengear.configdal.datasource.nacos.ConfigdalNacosDatasourceFactory");
//        props.put(PropertyKeyConst.SERVER_ADDR, serverAddr);
//        props.setProperty("secretKey", "");
        props.setProperty("namespace", "");
//        props.setProperty("ramRoleName", "");
//        props.setProperty("username", "");
//        props.setProperty("enableRemoteSyncConfig", "false");
//        props.setProperty("configLongPollTimeout", "");
//        props.setProperty("configRetryTime", "");
//        props.setProperty("encode", "");
        props.setProperty("serverAddr", "");
//        props.setProperty("maxRetry", "");
//        props.setProperty("clusterName", "");
//        props.setProperty("password", "");
//        props.setProperty("accessKey", "");
//        props.setProperty("endpoint", "");

        OpenGearConfigClient client = new OpenGearConfigClient(props);
        CompositedConfigDalDatasource configdalDatasources = client.getCompositedConfigDalDatasource();
        Object content = configdalDatasources.get("key");
        System.out.println(content);
    }
}
