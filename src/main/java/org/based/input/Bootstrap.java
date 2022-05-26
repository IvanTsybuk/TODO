package org.based.input;

import com.zaxxer.hikari.HikariDataSource;

public class Bootstrap {

    private static final String URL_DEFAULT = "jdbc:postgresql://localhost:5432/todoApp";
    private static final String USER_DEFAULT = "postgres";
    private static final String PASSWORD_DEFAULT = "postgres";

    public static void main(String[] args) {
        System.out.println(System.getenv("URL"));
        System.out.println(System.getenv("USER"));
        System.out.println(System.getenv("PASSWORD"));
        try (HikariDataSource hikariDataSource =
                     getDataSource("URL", "USER", "PASSWORD")) {
            Context context = new Context(hikariDataSource);
            context.startApp();
        }
    }
    public static HikariDataSource getDataSource(String url, String user, String password) {
        String urlEnvironment = System.getenv(url);
        if (urlEnvironment == null) {
            urlEnvironment = URL_DEFAULT;
        }
        String userEnvironment = System.getenv(user);
        if (userEnvironment == null) {
            userEnvironment = USER_DEFAULT;
        }
        String passwordEnvironment = System.getenv(password);
        if (passwordEnvironment == null) {
            passwordEnvironment = PASSWORD_DEFAULT;
        }
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(urlEnvironment);
        dataSource.setUsername(userEnvironment);
        dataSource.setPassword(passwordEnvironment);
        return dataSource;
    }
}
