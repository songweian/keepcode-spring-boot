package org.opengear.configdal.client;

enum ClientType implements ConfigdalConfigSpec {
    NACOS {
        @Override
        public String getConfigPrefix() {
            return "nacos";
        }
    },
    APOLLO {
        @Override
        public String getConfigPrefix() {
            return "apollo";
        }
    },
    SPRING_CONFIG {
        @Override
        public String getConfigPrefix() {
            return "spring.config";
        }
    };

    public static ClientType val(String clientType) {
        if (clientType == null) {
            return null;
        }
        for (ClientType e : values()) {
            if (e.name().equalsIgnoreCase(clientType)) {
                return e;
            }
        }
        return null;
    }
}
