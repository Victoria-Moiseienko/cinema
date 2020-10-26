package vic.cinema.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import vic.cinema.dao.MovieDao;
import vic.cinema.model.Movie;
import vic.cinema.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {
    private MovieDao movieDao;

    public MovieServiceImpl(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }

    @Override
    public Movie getById(Long id) {
        return movieDao.getById(id);
    }
}
