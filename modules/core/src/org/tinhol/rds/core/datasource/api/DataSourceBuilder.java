package org.tinhol.rds.core.datasource.api;

import javax.sql.DataSource;

public interface DataSourceBuilder {
    DataSource buildDataSource(DataSourceSettingsDTO dataSourceSettingsDTO);
}
