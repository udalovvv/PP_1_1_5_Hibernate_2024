package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL_KEY = "db.url";
    private static final String USERNAME_KEY = "db.username";
    private static final String PASSWORD_KEY = "db.password";

    public static Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(
                    PropertiesUtil.get(URL_KEY),
                    PropertiesUtil.get(USERNAME_KEY),
                    PropertiesUtil.get(PASSWORD_KEY));
            System.out.println("Connection OK");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }


    public static Session getSession(){
        try {
            Configuration configuration = new Configuration()
                    .addAnnotatedClass(User.class);
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            System.out.println("Connection OK");
            return sessionFactory.getCurrentSession();
        } catch (Exception e) {
            e.getStackTrace();
        }
        return null;
    }

}
