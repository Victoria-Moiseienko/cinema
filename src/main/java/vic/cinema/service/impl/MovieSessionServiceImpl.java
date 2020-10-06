package vic.cinema.service.impl;

import java.time.LocalDate;
import java.util.List;
import vic.cinema.dao.MovieSessionDao;
import vic.cinema.lib.Inject;
import vic.cinema.lib.Service;
import vic.cinema.model.MovieSession;
import vic.cinema.service.MovieSessionService;

@Service
public class MovieSessionServiceImpl implements MovieSessionService {
    @Inject
    private MovieSessionDao movieSessionDao;

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        List<MovieSession> movieSessionList = movieSessionDao.findAvailableSessions(movieId, date);
        return movieSessionList;
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        return movieSessionDao.add(movieSession);
    }
}
