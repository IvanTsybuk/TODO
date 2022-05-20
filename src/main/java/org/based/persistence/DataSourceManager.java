package org.based.persistence;

import com.zaxxer.hikari.HikariDataSource;

public class DataSourceManager {
    private final String url;
    private final String user;
    private final String password;
    public DataSourceManager(String url, String user, String password) {
        this.url = System.getenv(url);
        this.user = System.getenv(user);
        this.password = System.getenv(password);
    }
    public HikariDataSource configureDataSource() {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(url);
        ds.setUsername(user);
        ds.setPassword(password);
        return ds;
    }
}
