package vic.cinema.service.impl;

import java.util.List;
import vic.cinema.dao.MovieDao;
import vic.cinema.lib.Inject;
import vic.cinema.lib.Service;
import vic.cinema.model.Movie;
import vic.cinema.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {
    @Inject
    private MovieDao movieDao;

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }
}
