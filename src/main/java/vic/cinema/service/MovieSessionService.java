package vic.cinema.service;

import java.time.LocalDate;
import java.util.List;
import vic.cinema.model.MovieSession;

public interface MovieSessionService {
    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);

    MovieSession add(MovieSession session);
}
