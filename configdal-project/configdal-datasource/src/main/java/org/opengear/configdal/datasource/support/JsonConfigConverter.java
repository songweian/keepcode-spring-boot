package org.opengear.configdal.datasource.support;

import org.opengear.configdal.datasource.ConfigFormat;
import org.opengear.configdal.datasource.ConfigFormatConverter;

import java.util.Set;

public class JsonConfigConverter implements ConfigFormatConverter {

    @Override
    public String convert(String source) {
        return null;
    }

    @Override
    public ConfigFormat sourceFormat() {
        return ConfigFormat.JSON;
    }

    @Override
    public Set<ConfigFormat> targetFormat() {
        return Set.of(ConfigFormat.YAML);
    }

}
