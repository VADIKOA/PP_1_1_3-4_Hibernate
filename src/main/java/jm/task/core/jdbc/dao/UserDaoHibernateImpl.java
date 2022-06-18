package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try (session) {
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS `javapp`.`users` (`id` INT NOT NULL AUTO_INCREMENT," +
                    "`name` VARCHAR(45) NOT NULL,`lastName` VARCHAR(45) NOT NULL," +
                    "`age` TINYINT(3) NOT NULL, PRIMARY KEY (`id`)," +
                    "UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session  = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            session.createSQLQuery("DROP TABLE `javapp`.`users`").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session  = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            session.save(new User(name, lastName, age));
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session  = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            User user = session.get(User.class, id);
            session.remove(user);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session  = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        List<User> userList = session.createQuery("SELECT u FROM User u", User.class).getResultList();

        transaction.commit();
        session.close();

        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Session session  = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            session.createQuery("DELETE FROM User").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    public int countUsersTable() {
        int count;
        Session session  = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        count = (int) ((long)session.createQuery("SELECT count(*) FROM User").getSingleResult());

        transaction.commit();
        session.close();
        return --count;
    }
}
