package org.opengear.configdal.support;

public class ConfigdalClientException extends RuntimeException {

    public ConfigdalClientException(String clientTypeError) {
        super(clientTypeError);
    }
}
