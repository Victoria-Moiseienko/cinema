package vic.cinema.mapper;

import org.springframework.stereotype.Component;
import vic.cinema.dto.TicketResponseDto;
import vic.cinema.model.Ticket;

@Component
public class TicketMapper {
    private final MovieSessionMapper movieSessionMapper;

    public TicketMapper(MovieSessionMapper movieSessionMapper) {
        this.movieSessionMapper = movieSessionMapper;
    }

    public TicketResponseDto toDto(Ticket ticket) {
        TicketResponseDto dto = new TicketResponseDto();
        dto.setId(ticket.getId());
        dto.setMovieSessionResponseDto(
                movieSessionMapper.getMovieSessionResponseDto(ticket.getMovieSession()));
        return dto;
    }
}
