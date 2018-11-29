package org.tinhol.rds.core.datasource.impl;

import com.haulmont.bali.db.QueryRunner;
import org.tinhol.rds.core.datasource.api.DataSourceSettingsDTO;
import org.tinhol.rds.core.datasource.api.DataSourceSettingsLoader;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DbDataSourceSettingsLoader implements DataSourceSettingsLoader {
    private DataSource mainDs;

    public DbDataSourceSettingsLoader(DataSource mainDs) {
        this.mainDs = mainDs;
    }

    @Override
    public List<DataSourceSettingsDTO> loadDataSourceSettings() {
        QueryRunner queryRunner = new QueryRunner(mainDs);
        List<DataSourceSettingsDTO> dataSourceSettings = Collections.emptyList();

        try {
            dataSourceSettings = queryRunner.query(
                    "select driver_class, url, username, password, weight from RDS_DATA_SOURCE_SETTINGS where active is true",
                    rs -> {
                        List<DataSourceSettingsDTO> dtos = new ArrayList<>();
                        while (rs.next()) {
                            DataSourceSettingsDTO dto = new DataSourceSettingsDTO();
                            dto.setDriverClassName(rs.getString(1));
                            dto.setUrl(rs.getString(2));
                            dto.setUsername(rs.getString(3));
                            dto.setPassword(rs.getString(4));
                            dtos.add(dto);
                        }

                        return dtos;
                    });
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dataSourceSettings;
    }
}
