package vic.cinema;

import vic.cinema.lib.Injector;
import vic.cinema.model.Movie;
import vic.cinema.service.MovieService;

public class Main {
    private static Injector injector = Injector.getInstance("vic.cinema");

    public static void main(String[] args) {
        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        movieService.add(movie);
        movieService.getAll().forEach(System.out::println);
    }
}
