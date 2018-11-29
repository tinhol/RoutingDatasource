package org.tinhol.rds.config;

import com.haulmont.cuba.core.config.Config;
import com.haulmont.cuba.core.config.Property;
import com.haulmont.cuba.core.config.Source;
import com.haulmont.cuba.core.config.SourceType;

@Source(type = SourceType.DATABASE)
public interface RoutingDataSourceConfig extends Config {

    @Property("org.tinhol.rds.entities.allowedToReadFromReplicaRegexp")
    String getAllowedToReadFromReplicaEntitiesRegexp();
    void getAllowedToReadFromReplicaEntitiesRegexp(String regexp);

}
