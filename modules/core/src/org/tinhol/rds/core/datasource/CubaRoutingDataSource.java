package org.tinhol.rds.core.datasource;

import org.apache.commons.lang3.BooleanUtils;
import org.tinhol.rds.core.datasource.api.DataSourceBuilder;
import org.tinhol.rds.core.datasource.api.DataSourceRouter;
import org.tinhol.rds.core.datasource.api.DataSourceSettingsDTO;
import org.tinhol.rds.core.datasource.api.DataSourceSettingsLoader;
import org.tinhol.rds.core.datasource.impl.DbDataSourceSettingsLoader;
import org.tinhol.rds.core.datasource.impl.HikariDataSourceBuilder;
import org.tinhol.rds.core.datasource.impl.WeightedRandomRouter;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CubaRoutingDataSource extends AbstractRoutingDataSource {
    protected DataSourceBuilder dataSourceBuilder;
    protected DataSourceSettingsLoader dataSourceSettingsLoader;
    protected DataSourceRouter dataSourceRouter;

    protected DataSource mainDs;
    protected int mainDsWeight;

    public CubaRoutingDataSource(DataSource mainDs) {
        this.mainDs = mainDs;
        this.mainDsWeight = 1;
    }

    public void reloadConfiguration() {
        initialize();
    }

    @Override
    protected void preInitialize() {
        dataSourceBuilder = new HikariDataSourceBuilder();
        dataSourceSettingsLoader = new DbDataSourceSettingsLoader(mainDs);
        dataSourceRouter = new WeightedRandomRouter();

        Map<Object, Object> dataSources = new HashMap<>();
        dataSources.put(0, mainDs);
        List<DataSourceSettingsDTO> dataSourceSettingsDTOS = dataSourceSettingsLoader.loadDataSourceSettings();
        for (int i = 0; i < dataSourceSettingsDTOS.size(); i++) {
            DataSourceSettingsDTO replicaSettings = dataSourceSettingsDTOS.get(i);
            if (replicaSettings.isValid()) {
                dataSources.put(i + 1, dataSourceBuilder.buildDataSource(replicaSettings));
            }
        }
        dataSourceRouter.init(mainDs, mainDsWeight, dataSourceSettingsDTOS);

        setDefaultTargetDataSource(mainDs);
        setTargetDataSources(dataSources);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return dataSourceRouter.determineCurrentLookupKey();
    }

    public static void setReadOnly(Boolean useReadOnly) {
        ReadFromReplicaHolder.set(useReadOnly);
    }

    public static boolean isReadOnly() {
        return BooleanUtils.isTrue(ReadFromReplicaHolder.get());
    }

}
