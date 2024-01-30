package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao{

    @Override
    public void createUsersTable() {
        Session session = Util.getSession();
        Transaction transaction = session.beginTransaction();

        String sql = """
                CREATE TABLE IF NOT EXISTS UsersTable(
                id MEDIUMINT not null AUTO_INCREMENT,
                name VARCHAR(255),
                lastName VARCHAR(255),
                age INTEGER,
                PRIMARY KEY (id)
                )
                """;

        session.createSQLQuery(sql).executeUpdate();
        transaction.commit();
        session.close();

    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSession();
        Transaction transaction = session.beginTransaction();

        String sql = "DROP TABLE IF EXISTS UsersTable";
        session.createSQLQuery(sql).executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(new User(name, lastName, age));
        transaction.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSession();
        Transaction transaction = session.beginTransaction();

        session.delete(session.get(User.class, id));

        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session =Util.getSession();
        Transaction transaction = session.beginTransaction();

        List<User> userList = session.createQuery("FROM User").getResultList();

        transaction.commit();
        session.close();
        return userList;

    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSession();
        Transaction transaction = session.beginTransaction();

        session.createSQLQuery("DELETE FROM UsersTable").executeUpdate();

        transaction.commit();
        session.close();
    }
}
