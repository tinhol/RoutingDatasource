package org.tinhol.rds.service;

import com.haulmont.cuba.core.global.AppBeans;
import org.springframework.stereotype.Service;
import org.tinhol.rds.core.datasource.CubaRoutingDataSource;

@Service(DataSourceService.NAME)
public class DataSourceServiceBean implements DataSourceService {

    @Override
    public void reloadDataSources() {
        CubaRoutingDataSource cubaDataSource = AppBeans.get("cubaDataSource");
        cubaDataSource.reloadConfiguration();
    }
}