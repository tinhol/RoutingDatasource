package org.tinhol.rds.core.datasource.api;

import java.util.List;

public interface DataSourceSettingsLoader {
    List<DataSourceSettingsDTO> loadDataSourceSettings();
}
