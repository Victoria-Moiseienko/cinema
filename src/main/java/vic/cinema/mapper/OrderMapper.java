package vic.cinema.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import vic.cinema.dto.OrderResponseDto;
import vic.cinema.model.Order;
import vic.cinema.model.Ticket;

@Component
public class OrderMapper {
    public OrderResponseDto toDto(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setOrderId(order.getId());
        dto.setUserId(order.getUser().getId());
        dto.setOrderDate(order.getOrderDate());
        List<Long> ticketIdList = order.getTickets().stream()
                .map(Ticket::getId)
                .collect(Collectors.toList());
        dto.setTicketIds(ticketIdList);
        return dto;
    }
}
