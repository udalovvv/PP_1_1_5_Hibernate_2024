package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        PreparedStatement preparedStatement = null;

        String sql = """
                CREATE TABLE IF NOT EXISTS UsersTable(
                id MEDIUMINT not null AUTO_INCREMENT,
                name VARCHAR(255),
                lastName VARCHAR(255),
                age INTEGER,
                PRIMARY KEY (id)
                )
                """;
        try (Connection connection = Util.getConnection()) {
            connection.prepareStatement(sql).execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        PreparedStatement preparedStatement = null;

        String sql = """
                DROP TABLE IF EXISTS UsersTable
                """;

        try (Connection connection = Util.getConnection()) {
            connection.prepareStatement(sql).executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        PreparedStatement preparedStatement = null;
        String sql = """
                    INSERT INTO kata.UsersTable (
                    name, lastName, age
                    ) VALUES (?, ?, ?);
                    """;

        try (Connection connection = Util.getConnection()) {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM kata.UsersTable WHERE ID=?";

        try (Connection connection = Util.getConnection()){
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<User> userList = new ArrayList<>();
        String sql = "SELECT id, name, lastName, age from kata.UsersTable";

        try (Connection connection = Util.getConnection()){
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                userList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    public void cleanUsersTable() {
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM kata.userstable";

        try (Connection connection = Util.getConnection()){
            connection.prepareStatement(sql).executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
