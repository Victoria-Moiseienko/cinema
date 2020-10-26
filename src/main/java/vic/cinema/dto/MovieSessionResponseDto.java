package vic.cinema.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class MovieSessionResponseDto {
    private Long id;
    private LocalDateTime showTime;
    private Long movieId;
    private String movieTitle;
    private Long cinemaHallId;
}
