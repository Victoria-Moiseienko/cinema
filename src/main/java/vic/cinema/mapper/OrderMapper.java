package vic.cinema.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import vic.cinema.dto.OrderResponseDto;
import vic.cinema.dto.TicketResponseDto;
import vic.cinema.model.Order;

@Component
public class OrderMapper {
    private final TicketMapper ticketMapper;

    public OrderMapper(TicketMapper ticketMapper) {
        this.ticketMapper = ticketMapper;
    }

    public OrderResponseDto toDto(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setUserId(order.getUser().getId());
        dto.setOrderDate(order.getOrderDate());
        List<TicketResponseDto> ticketDtoList = order.getTickets().stream()
                .map(ticketMapper::toDto)
                .collect(Collectors.toList());
        dto.setTickets(ticketDtoList);
        return dto;
    }
}
