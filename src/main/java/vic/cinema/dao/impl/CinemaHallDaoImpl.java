package vic.cinema.dao.impl;

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import vic.cinema.dao.CinemaHallDao;
import vic.cinema.exeptions.DataProcessingException;
import vic.cinema.model.CinemaHall;

@Repository
public class CinemaHallDaoImpl implements CinemaHallDao {
    private static final Logger logger = Logger.getLogger(CinemaHallDaoImpl.class);
    private final SessionFactory sessionFactory;

    public CinemaHallDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
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
        try (Session session = sessionFactory.openSession()) {
            Query<CinemaHall> getAllQuery =
                    session.createQuery("from CinemaHall ", CinemaHall.class);
            return getAllQuery.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all Cinema Halls", e);
        }
    }

    @Override
    public CinemaHall getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Query<CinemaHall> query = session.createQuery(
                    "from CinemaHall where id = :cinemaHallId", CinemaHall.class);
            query.setParameter("cinemaHallId", id);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException("Failed get Cinema Hall by id " + id, e);
        }
    }
}
