package org.based.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import lombok.SneakyThrows;
import org.based.domain.User;

public class UserDataBaseRepository {
    private static final String TABLE_NAME = "users";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private final ConnectionConfiguration connectionConfiguration;
    public UserDataBaseRepository(ConnectionConfiguration connectionConfiguration) {
        this.connectionConfiguration = connectionConfiguration;
    }
    @SneakyThrows
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        String selectSql = "select * from " + TABLE_NAME;
        final PreparedStatement preparedStatement =
                connectionConfiguration.getConnection().prepareStatement(selectSql);
        final ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            User user = new User();
            user.setName(resultSet.getString(NAME));
            user.setUserSurName(resultSet.getString(SURNAME));
            userList.add(user);
        }
        return userList;
    }
    @SneakyThrows
    public void save(User user) {
        final String insert = "INSERT INTO " + TABLE_NAME + "(name, surname)"
                + "VALUES ('" + user.getName() + "', '" + user.getUserSurName() + "')";
        final Statement statement =  connectionConfiguration.getConnection().createStatement();
        statement.executeUpdate(insert);
    }
    @SneakyThrows
    public User findUser(String name) {
        final User user = new User();
        String selectSql = "SELECT * FROM " + TABLE_NAME + " WHERE name = ?";
        final PreparedStatement preparedStatement =
                connectionConfiguration.getConnection().prepareStatement(selectSql);
        preparedStatement.setString(1, name);
        final ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            user.setName(resultSet.getString(NAME));
            user.setUserSurName(resultSet.getString(SURNAME));
        }
        return user;
    }
}
