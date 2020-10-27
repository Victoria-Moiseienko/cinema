package vic.cinema.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import vic.cinema.dto.ShoppingCartResponseDto;
import vic.cinema.model.ShoppingCart;
import vic.cinema.model.Ticket;

@Component
public class ShoppingCartMapper {
    public ShoppingCartResponseDto toDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto dto = new ShoppingCartResponseDto();
        dto.setId(shoppingCart.getId());
        List<Long> ticketIdList = shoppingCart.getTickets().stream()
                .map(Ticket::getId)
                .collect(Collectors.toList());
        dto.setTicketIds(ticketIdList);
        return dto;
    }
}
