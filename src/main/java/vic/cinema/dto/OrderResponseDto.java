package vic.cinema.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class OrderResponseDto {
    private Long userId;
    private LocalDateTime orderDate;
    private List<TicketResponseDto> tickets;
}
