package org.based.input;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;

public class Bootstrap {

    private static final String URL_DEFAULT = "jdbc:postgresql://localhost:5432/todoApp";
    private static final String USER_DEFAULT = "postgres";
    private static final String PASSWORD_DEFAULT = "postgres";
    private static final String DATABASE_URL = "URL";
    private static final String DATABASE_USER = "USER";
    private static final String DATABASE_PASSWORD = "PASSWORD";

    public static void main(String[] args) {
        HikariDataSource hikariDataSource = (HikariDataSource) createDataSource();
        Context context = new Context(hikariDataSource);
        context.startApp();
    }
    public static DataSource createDataSource() {
        String urlEnvironment = getEnvVariable(DATABASE_URL, URL_DEFAULT);
        String userEnvironment = getEnvVariable(DATABASE_USER, USER_DEFAULT);
        String passwordEnvironment = getEnvVariable(DATABASE_PASSWORD, PASSWORD_DEFAULT);
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(urlEnvironment);
        dataSource.setUsername(userEnvironment);
        dataSource.setPassword(passwordEnvironment);
        return dataSource;
    }
    private static String getEnvVariable(String sourceValue, String defaultValue) {
        String environmentVariable = System.getenv(sourceValue);
        if (environmentVariable == null) {
            environmentVariable = defaultValue;
        }
        return environmentVariable;
    }
}
