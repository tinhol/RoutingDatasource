package org.tinhol.rds.core;

import com.haulmont.cuba.core.app.DataManagerBean;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.LoadContext;
import org.apache.commons.lang3.StringUtils;
import org.tinhol.rds.config.RoutingDataSourceConfig;
import org.tinhol.rds.core.datasource.ReadFromReplicaHolder;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RoutingDataManagerBean extends DataManagerBean {
    @Inject
    protected RoutingDataSourceConfig routingDataSourceConfig;

    @Override
    public <E extends Entity> List<E> loadList(LoadContext<E> context) {
        try {
            List<String> regexps = Stream.of(routingDataSourceConfig.getAllowedToReadFromReplicaEntitiesRegexp())
                    .filter(StringUtils::isNotBlank)
                    .flatMap(regexp -> Arrays.stream(regexp.split(";")))
                    .collect(Collectors.toList());

            if (context.getMetaClass() != null
                    && regexps.stream().anyMatch(regexp -> context.getMetaClass().matches(regexp))) {
                ReadFromReplicaHolder.set(true);
            }
            return super.loadList(context);
        } finally {
            ReadFromReplicaHolder.set(false);
        }
    }
}
