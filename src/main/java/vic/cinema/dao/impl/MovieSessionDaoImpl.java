package vic.cinema.dao.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import vic.cinema.dao.MovieSessionDao;
import vic.cinema.exeptions.DataProcessingException;
import vic.cinema.model.MovieSession;

@Repository
public class MovieSessionDaoImpl implements MovieSessionDao {
    private static final Logger logger = Logger.getLogger(MovieSessionDaoImpl.class);
    private final SessionFactory sessionFactory;

    public MovieSessionDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(movieSession);
            transaction.commit();
            logger.info("MovieSession has been added:\n" + movieSession);
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Movie Session has not been added\n", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = sessionFactory.openSession()) {
            Query<MovieSession> query = session.createQuery(
                    "FROM MovieSession WHERE movie_id = :movieId "
                            + "AND showtime BETWEEN :startTime AND :endTime",
                    MovieSession.class);
            query.setParameter("movieId", movieId);
            query.setParameter("startTime", date.atTime(LocalTime.MIN));
            query.setParameter("endTime", date.atTime(LocalTime.MAX));
            return query.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Available Sessions have not been selected\n", e);
        }
    }

    @Override
    public List<MovieSession> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<MovieSession> getAllMovieSessionsQuery =
                    session.createQuery("from MovieSession", MovieSession.class);
            return getAllMovieSessionsQuery.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all movie sessions", e);
        }
    }
}
