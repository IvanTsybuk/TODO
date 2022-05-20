package org.based.input;

import org.based.persistence.DataSourceManager;

public class Bootstrap {

    private static final String URL_TODO = "URL_TODO";
    private static final String USER_TODO = "USER_TODO";
    private static final String PASSWORD_TODO = "PASSWORD_TODO";

    public static void main(String[] args) {
        DataSourceManager dataSourceManager =
                new DataSourceManager(URL_TODO, USER_TODO, PASSWORD_TODO);
        Context context =
                new Context(dataSourceManager.configureDataSource());
        context.startApp();
        context.closeApp();
    }
}
