package vic.cinema.dao.impl;

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import vic.cinema.dao.CinemaHallDao;
import vic.cinema.exeptions.DataProcessingException;
import vic.cinema.lib.Dao;
import vic.cinema.model.CinemaHall;
import vic.cinema.util.HibernateUtil;

@Dao
public class CinemaHallDaoImpl implements CinemaHallDao {
    private static final Logger logger = Logger.getLogger(CinemaHallDaoImpl.class);

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(cinemaHall);
            transaction.commit();
            logger.info("cinemaHall has been added:\n" + cinemaHall);
            return cinemaHall;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("CinemaHall has not been added\n", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<CinemaHall> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<CinemaHall> getAllQuery =
                    session.createQuery("from CinemaHall ", CinemaHall.class);
            return getAllQuery.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all Cinema Halls", e);
        }
    }
}
