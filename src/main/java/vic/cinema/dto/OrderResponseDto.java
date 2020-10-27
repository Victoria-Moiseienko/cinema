package vic.cinema.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class OrderResponseDto {
    private Long orderId;
    private Long userId;
    private LocalDateTime orderDate;
    private List<Long> ticketIds;
}
