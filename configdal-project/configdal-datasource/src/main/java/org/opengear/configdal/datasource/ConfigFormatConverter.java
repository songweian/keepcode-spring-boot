package org.opengear.configdal.datasource;

import java.util.Set;

public interface ConfigFormatConverter<S, T> {

    String convert(String source);

    ConfigFormat sourceFormat();

    Set<ConfigFormat> targetFormat();

    default boolean shouldSupport(ConfigFormat tarterFormat) {
        if (targetFormat() == null) {
            throw new RuntimeException("");
        }
        return targetFormat().contains(tarterFormat);
    }
}
