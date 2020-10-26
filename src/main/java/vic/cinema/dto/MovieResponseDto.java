package vic.cinema.dto;

import lombok.Data;

@Data
public class MovieResponseDto {
    private Long movieId;
    private String title;
    private String description;
}
