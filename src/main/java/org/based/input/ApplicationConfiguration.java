package org.based.input;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Log4j2
public class ApplicationConfiguration {
    private static final String URL_DEFAULT = "jdbc:postgresql://localhost:5432/todoApp";
    private static final String USER_DEFAULT = "postgres";
    private static final String PASSWORD_DEFAULT = "postgres";
    private static final String DATABASE_URL = "URL";
    private static final String DATABASE_USER = "USER";
    private static final String DATABASE_PASSWORD = "PASSWORD";
    @Bean
    public DataSource createDataSource() {
        log.info("Creating data source");
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(getEnvVariable(DATABASE_URL, URL_DEFAULT));
        log.debug(String.format("Jdbc Url:%s", dataSource.getJdbcUrl()));
        dataSource.setUsername(getEnvVariable(DATABASE_USER, USER_DEFAULT));
        log.debug(String.format("Jdbc username:%s", dataSource.getJdbcUrl()));
        dataSource.setPassword(getEnvVariable(DATABASE_PASSWORD, PASSWORD_DEFAULT));
        log.debug("data source password is set");
        return dataSource;
    }
    private @NotNull String getEnvVariable(@Nullable String sourceValue,
                                           @NotNull String defaultValue) {
        String environmentVariable = System.getenv(sourceValue);
        if (environmentVariable == null || environmentVariable.isBlank()) {
            log.warn("Default settings for datasource are set");
            environmentVariable = defaultValue;
        }
        return environmentVariable;
    }
}
