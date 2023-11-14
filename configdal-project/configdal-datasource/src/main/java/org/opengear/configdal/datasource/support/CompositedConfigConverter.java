package org.opengear.configdal.datasource.support;

import org.opengear.configdal.datasource.ConfigFormat;
import org.opengear.configdal.datasource.ConfigFormatConverter;

import java.util.HashMap;
import java.util.Map;

public class CompositedConfigConverter {

    private final Map<ConfigFormat, ConfigFormatConverter> converts = new HashMap<>();

    public CompositedConfigConverter() {
        converts.put(ConfigFormat.JSON, new JsonConfigConverter());
        converts.put(ConfigFormat.YAML, new YamlConfigConverter());
        converts.put(ConfigFormat.HTML, new HtmlConfigConverter());
        converts.put(ConfigFormat.XML, new XmlConfigConverter());
        converts.put(ConfigFormat.TEXT, new TextConfigConverter());
        converts.put(ConfigFormat.PROPERTY, new PropertiesConfigConverter());
    }

    public String convert(String content, ConfigFormat sourceFormat, ConfigFormat tarterFormat) {
        ConfigFormatConverter configFormatConvert = converts.get(sourceFormat);
        if (configFormatConvert == null) {
            throw new RuntimeException("");
        }
        if (!configFormatConvert.shouldSupport(tarterFormat)) {
            throw new RuntimeException("");
        }
        return configFormatConvert.convert(content);
    }

}
