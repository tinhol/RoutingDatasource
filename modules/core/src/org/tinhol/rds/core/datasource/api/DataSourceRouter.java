package org.tinhol.rds.core.datasource.api;

import javax.sql.DataSource;
import java.util.List;

public interface DataSourceRouter {
    void init(DataSource mainDs, Integer mainDsWeight, List<DataSourceSettingsDTO> replicaSettings);

    Object determineCurrentLookupKey();
}
