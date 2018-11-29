package org.tinhol.rds.web.datasource_settings;

import com.haulmont.cuba.gui.components.AbstractLookup;
import org.tinhol.rds.service.DataSourceService;

import javax.inject.Inject;

public class DataSourceSettingsBrowse extends AbstractLookup {
    @Inject
    private DataSourceService dataSourceService;

    public void reloadDataSources() {
        dataSourceService.reloadDataSources();
    }
}