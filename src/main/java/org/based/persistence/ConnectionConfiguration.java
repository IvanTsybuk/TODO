package org.based.persistence;

import java.sql.Connection;
import lombok.Getter;

public class ConnectionConfiguration {
    @Getter
    private final Connection connection;
    public ConnectionConfiguration(Connection connection) {
        this.connection = connection;
    }
}
