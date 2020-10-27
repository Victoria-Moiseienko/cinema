package vic.cinema.dto;

import lombok.Data;

@Data
public class TicketResponseDto {
    private Long id;
    private MovieSessionResponseDto movieSessionResponseDto;
}
