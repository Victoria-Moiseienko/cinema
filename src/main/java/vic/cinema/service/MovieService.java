package vic.cinema.service;

import java.util.List;
import vic.cinema.model.Movie;

public interface MovieService {
    Movie add(Movie movie);

    List<Movie> getAll();
}
