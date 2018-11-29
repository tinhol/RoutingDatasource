package org.tinhol.rds.core.datasource.impl;

import com.zaxxer.hikari.HikariDataSource;
import org.tinhol.rds.core.datasource.api.DataSourceBuilder;
import org.tinhol.rds.core.datasource.api.DataSourceSettingsDTO;

import javax.sql.DataSource;

public class HikariDataSourceBuilder implements DataSourceBuilder {
    @Override
    public DataSource buildDataSource(DataSourceSettingsDTO dataSourceSettingsDTO) {
        HikariDataSource jdbcDataSource = new HikariDataSource();
        jdbcDataSource.setJdbcUrl(dataSourceSettingsDTO.getUrl());
        jdbcDataSource.setUsername(dataSourceSettingsDTO.getUsername());
        jdbcDataSource.setPassword(dataSourceSettingsDTO.getPassword());
        jdbcDataSource.setDriverClassName(dataSourceSettingsDTO.getDriverClassName());
        jdbcDataSource.setMaximumPoolSize(20);
        return jdbcDataSource;
    }
}
