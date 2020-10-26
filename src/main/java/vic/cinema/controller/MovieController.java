package vic.cinema.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vic.cinema.dto.MovieRequestDto;
import vic.cinema.dto.MovieResponseDto;
import vic.cinema.mapper.MovieMapper;
import vic.cinema.model.Movie;
import vic.cinema.service.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final MovieMapper movieMapper;

    public MovieController(MovieService movieService, MovieMapper movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }

    @PostMapping
    public Movie addMovie(@RequestBody MovieRequestDto movieRequestDto) {
        Movie movie = movieService.add(movieMapper.getMovie(movieRequestDto));
        return movie;
    }

    @GetMapping
    public List<MovieResponseDto> getAllMovies() {
        List<MovieResponseDto> allMovieResponseDtos = movieService.getAll().stream()
                .map(movieMapper::getMovieResponseDto)
                .collect(Collectors.toList());
        return allMovieResponseDtos;
    }
}
