package vic.cinema.dao;

import java.util.List;
import vic.cinema.model.Movie;

public interface MovieDao {
    Movie add(Movie movie);

    List<Movie> getAll();

    Movie get(Long id);
}
