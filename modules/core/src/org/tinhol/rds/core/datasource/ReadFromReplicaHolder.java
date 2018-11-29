package org.tinhol.rds.core.datasource;

import org.springframework.core.NamedThreadLocal;

public final class ReadFromReplicaHolder {
    private ReadFromReplicaHolder() {
    }

    private static ThreadLocal<Boolean> readFromReplicaHolder = new NamedThreadLocal<>("Read From Replica Holder");

    public static void set(Boolean readOnly) {
        readFromReplicaHolder.set(readOnly);
    }

    public static Boolean get() {
        return readFromReplicaHolder.get();
    }
}
