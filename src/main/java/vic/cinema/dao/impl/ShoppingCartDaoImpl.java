package vic.cinema.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import vic.cinema.dao.ShoppingCartDao;
import vic.cinema.exeptions.DataProcessingException;
import vic.cinema.model.ShoppingCart;
import vic.cinema.model.User;

@Repository
public class ShoppingCartDaoImpl implements ShoppingCartDao {
    private static final Logger logger = Logger.getLogger(ShoppingCartDaoImpl.class);
    private final SessionFactory sessionFactory;

    public ShoppingCartDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ShoppingCart add(ShoppingCart shoppingCart) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(shoppingCart);
            transaction.commit();
            logger.info("ShoppingCart has been added:\n" + shoppingCart);
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
    public ShoppingCart getByUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Query<ShoppingCart> query = session.createQuery(
                    "FROM ShoppingCart cart "
                    + "JOIN FETCH cart.user user "
                    + "LEFT JOIN FETCH cart.tickets "
                    + "WHERE user.id = :userId",
                    ShoppingCart.class);
            query.setParameter("userId", user.getId());
            return query.uniqueResult();
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
            session = sessionFactory.openSession();
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
