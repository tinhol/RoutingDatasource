package org.tinhol.rds.core.datasource.impl;

import org.apache.commons.lang3.BooleanUtils;
import org.tinhol.rds.core.datasource.api.DataSourceRouter;
import org.tinhol.rds.core.datasource.api.DataSourceSettingsDTO;
import org.tinhol.rds.core.datasource.ReadFromReplicaHolder;
import org.tinhol.rds.core.util.WeightedRandom;

import javax.sql.DataSource;
import java.util.List;

public class WeightedRandomRouter implements DataSourceRouter {
    private WeightedRandom random = new WeightedRandom();

    @Override
    public void init(DataSource mainDs, Integer mainDsWeight, List<DataSourceSettingsDTO> replicaSettings) {
        random.addValue(0, mainDsWeight);

        for (int i = 0; i < replicaSettings.size(); i++) {
            random.addValue(i + 1, replicaSettings.get(i).getWeight());
        }
    }

    @Override
    public Object determineCurrentLookupKey() {
        Integer key = BooleanUtils.isTrue(ReadFromReplicaHolder.get()) ? random.randomValue() : null;
        return key;
    }
}
