package vic.cinema.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import vic.cinema.dao.TicketDao;
import vic.cinema.exeptions.DataProcessingException;
import vic.cinema.lib.Dao;
import vic.cinema.model.Ticket;
import vic.cinema.util.HibernateUtil;

@Dao
public class TicketDaoImpl implements TicketDao {
    @Override
    public Ticket add(Ticket ticket) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(ticket);
            transaction.commit();
            return ticket;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Ticket for user with email ["
                    + ticket.getUser().getEmail() + "] has not been added\n", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
