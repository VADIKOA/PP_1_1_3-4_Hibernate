package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService{
    public void createUsersTable() throws SQLException {
        UserDao userDao = new UserDaoHibernateImpl();
        userDao.createUsersTable();
    }

    public void dropUsersTable() throws SQLException {
        UserDao userDao = new UserDaoHibernateImpl();
        userDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        UserDao userDao = new UserDaoHibernateImpl();
        userDao.saveUser(name, lastName ,age);
    }

    public void removeUserById(long id) throws SQLException {
        UserDao userDao = new UserDaoHibernateImpl();
        userDao.removeUserById(id);
    }

    public List<User> getAllUsers() throws SQLException {
        UserDao userDao = new UserDaoHibernateImpl();
        return userDao.getAllUsers();
    }

    public void cleanUsersTable() throws SQLException {
        UserDao userDao = new UserDaoHibernateImpl();
        userDao.cleanUsersTable();
    }

    public int countUsersTable() throws SQLException {
        UserDao userDao = new UserDaoHibernateImpl();
        return userDao.countUsersTable();
    }
}
