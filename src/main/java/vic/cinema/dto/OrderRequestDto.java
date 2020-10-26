package vic.cinema.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class OrderRequestDto {
    private LocalDateTime orderDate;
    private List<TicketResponseDto> tickets;
}
