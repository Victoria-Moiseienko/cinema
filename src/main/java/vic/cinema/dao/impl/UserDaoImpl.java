package vic.cinema.dao.impl;

import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import vic.cinema.dao.UserDao;
import vic.cinema.exeptions.DataProcessingException;
import vic.cinema.lib.Dao;
import vic.cinema.model.User;
import vic.cinema.util.HibernateUtil;

@Dao
public class UserDaoImpl implements UserDao {
    @Override
    public User add(User user) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();

            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("User with email ["
                    + user.getEmail() + "] has not been added\n", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query =
                    session.createQuery("FROM User WHERE email = :userEmail ", User.class);
            query.setParameter("userEmail", email);
            return query.uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException(
                    "User has not been found: email[" + email + "]", e);
        }
    }
}
