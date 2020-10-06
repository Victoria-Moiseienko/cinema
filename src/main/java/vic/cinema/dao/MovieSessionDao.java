package vic.cinema.dao;

import java.time.LocalDate;
import java.util.List;
import vic.cinema.model.MovieSession;

public interface MovieSessionDao {
    MovieSession add(MovieSession movieSession);

    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);
}
