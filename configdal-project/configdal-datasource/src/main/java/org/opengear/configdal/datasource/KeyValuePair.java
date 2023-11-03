package org.opengear.configdal.datasource;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class KeyValuePair {
    private ModifyType modifyType;
    private String key;
    private Object oldValue;
    private Object newValue;
}
