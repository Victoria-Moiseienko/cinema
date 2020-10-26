package vic.cinema.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vic.cinema.dto.MovieSessionRequestDto;
import vic.cinema.dto.MovieSessionResponseDto;
import vic.cinema.mapper.MovieSessionMapper;
import vic.cinema.model.MovieSession;
import vic.cinema.service.MovieSessionService;

@RestController
@RequestMapping("/movie-sessions")
public class MovieSessionController {
    private final MovieSessionService movieSessionService;
    private final MovieSessionMapper movieSessionMapper;

    public MovieSessionController(MovieSessionService movieSessionService,
                                  MovieSessionMapper movieSessionMapper) {
        this.movieSessionService = movieSessionService;
        this.movieSessionMapper = movieSessionMapper;
    }

    @PostMapping
    public MovieSession addMovieSession(
            @RequestBody MovieSessionRequestDto movieSessionRequestDto) {
        MovieSession movieSession =
                movieSessionService.add(movieSessionMapper.getMovieSession(movieSessionRequestDto));
        return movieSession;
    }

    @GetMapping
    public List<MovieSessionResponseDto> getAllAvailableMovieSessions(
            @RequestParam Long movieId,
            @RequestParam LocalDate date) {
        List<MovieSessionResponseDto> availableMovieSessions
                = movieSessionService.findAvailableSessions(movieId, date).stream()
                .map(movieSessionMapper::getMovieSessionResponseDto)
                .collect(Collectors.toList());
        return availableMovieSessions;
    }
}
