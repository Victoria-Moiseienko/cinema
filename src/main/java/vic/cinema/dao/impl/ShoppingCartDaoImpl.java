package vic.cinema.dao.impl;

import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import vic.cinema.dao.ShoppingCartDao;
import vic.cinema.exeptions.DataProcessingException;
import vic.cinema.lib.Dao;
import vic.cinema.model.ShoppingCart;
import vic.cinema.model.User;
import vic.cinema.util.HibernateUtil;

@Dao
public class ShoppingCartDaoImpl implements ShoppingCartDao {
    @Override
    public ShoppingCart add(ShoppingCart shoppingCart) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(shoppingCart);
            transaction.commit();
            return shoppingCart;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Shopping Cart for user ["
                    + shoppingCart.getUser().getEmail() + "] has not been added\n", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<ShoppingCart> getByUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<ShoppingCart> query = session.createQuery(
                    "FROM ShoppingCart cart "
                    + "JOIN FETCH cart.user user "
                    + "LEFT JOIN FETCH cart.tickets "
                    + "WHERE user.id = :userId",
                    ShoppingCart.class);
            query.setParameter("userId", user.getId());
            return query.uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException(
                    "Shopping Cart for user [" + user.getEmail() + "] has not been found", e);
        }
    }

    @Override
    public void update(ShoppingCart shoppingCart) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(shoppingCart);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Shopping Cart for user ["
                    + shoppingCart.getUser().getEmail() + "] has not been updated\n", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}