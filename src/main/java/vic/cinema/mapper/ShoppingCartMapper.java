package vic.cinema.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import vic.cinema.dto.ShoppingCartResponseDto;
import vic.cinema.dto.TicketResponseDto;
import vic.cinema.model.ShoppingCart;

@Component
public class ShoppingCartMapper {
    private final TicketMapper ticketMapper;

    public ShoppingCartMapper(TicketMapper ticketMapper) {
        this.ticketMapper = ticketMapper;
    }

    public ShoppingCartResponseDto toDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto dto = new ShoppingCartResponseDto();
        dto.setId(shoppingCart.getId());
        List<TicketResponseDto> ticketDtoList = shoppingCart.getTickets().stream()
                .map(ticketMapper::toDto)
                .collect(Collectors.toList());
        dto.setTickets(ticketDtoList);
        return dto;
    }
}
