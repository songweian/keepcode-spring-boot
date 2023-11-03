package org.opengear.springframework.boot.configdal;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "opengear.configdal.nacos")
@Data
public class ConfigdalNacosProperties {
    private String serverAddr;
    private String namespace;
    private String dataId;
    private String group;

}
