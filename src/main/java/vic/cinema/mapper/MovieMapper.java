package vic.cinema.mapper;

import org.springframework.stereotype.Component;
import vic.cinema.dto.MovieRequestDto;
import vic.cinema.dto.MovieResponseDto;
import vic.cinema.model.Movie;

@Component
public class MovieMapper {
    public MovieResponseDto getMovieResponseDto(Movie movie) {
        MovieResponseDto responseDto = new MovieResponseDto();
        responseDto.setMovieId(movie.getId());
        responseDto.setDescription(movie.getDescription());
        responseDto.setTitle(movie.getTitle());
        return responseDto;
    }

    public Movie getMovie(MovieRequestDto movieRequestDto) {
        Movie movie = new Movie();
        movie.setDescription(movieRequestDto.getDescription());
        movie.setTitle(movieRequestDto.getTitle());
        return movie;
    }
}
