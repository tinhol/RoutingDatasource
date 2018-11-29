package org.tinhol.rds.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern("%s|name")
@Table(name = "RDS_DATA_SOURCE_SETTINGS")
@Entity(name = "rds$DataSourceSettings")
public class DataSourceSettings extends StandardEntity {
    private static final long serialVersionUID = 183503324784644382L;

    @Column(name = "NAME")
    protected String name;

    @NotNull
    @Column(name = "DRIVER_CLASS", nullable = false, length = 500)
    protected String driverClass;

    @NotNull
    @Column(name = "URL", nullable = false, length = 500)
    protected String url;

    @NotNull
    @Column(name = "USERNAME", nullable = false)
    protected String username;

    @Column(name = "PASSWORD")
    protected String password;

    @NotNull
    @Column(name = "WEIGHT", nullable = false)
    protected Integer weight;

    @Column(name = "ACTIVE")
    protected Boolean active;

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getActive() {
        return active;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getWeight() {
        return weight;
    }


}