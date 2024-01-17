package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoJDBCImpl();
//        userDao.saveUser("Ivan", "Ivanov", (byte) 20);
        List<User> userList = userDao.getAllUsers();
        for (User user: userList) {
            System.out.println(user);
        }
    }

}
