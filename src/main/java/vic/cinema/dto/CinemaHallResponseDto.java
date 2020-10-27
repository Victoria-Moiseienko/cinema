package vic.cinema.dto;

import lombok.Data;

@Data
public class CinemaHallResponseDto {
    private Long cinemaHallId;
    private Integer capacity;
    private String description;
}
