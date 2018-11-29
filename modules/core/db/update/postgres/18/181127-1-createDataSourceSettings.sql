create table RDS_DATA_SOURCE_SETTINGS (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255),
    DRIVER_CLASS varchar(500) not null,
    URL varchar(500) not null,
    USERNAME varchar(255) not null,
    PASSWORD varchar(255),
    WEIGHT integer not null,
    --
    primary key (ID)
);
