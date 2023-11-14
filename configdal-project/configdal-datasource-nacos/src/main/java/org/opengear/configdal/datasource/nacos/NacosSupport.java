package org.opengear.configdal.datasource.nacos;

public class NacosSupport {

    public static String dataId(String appId, String environment, String cluster, String group) {
        return appId + "_" + environment + "_" + cluster + "_" + group;
    }
}
