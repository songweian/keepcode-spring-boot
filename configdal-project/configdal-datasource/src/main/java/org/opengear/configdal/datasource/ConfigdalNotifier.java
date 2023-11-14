package org.opengear.configdal.datasource;

import java.util.HashSet;
import java.util.Set;

public class ConfigdalNotifier {
    private final Set<ConfigdalListener> configdalListeners = new HashSet<>();

    public void notifyConfigChanged(String datasourceId, String configInfo) {
        for (ConfigdalListener listener : configdalListeners) {
            try {
                listener.onConfigChanged(datasourceId, configInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void registListener(ConfigdalListener configdalListener) {
        configdalListeners.add(configdalListener);
    }

}
