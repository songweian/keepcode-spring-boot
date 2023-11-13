package org.opengear.configdal.datasource;

public interface ConfigdalListener {

    void onConfigChanged(String datasourceId, String changedContent);

}
