package vic.cinema.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vic.cinema.dto.OrderResponseDto;
import vic.cinema.mapper.OrderMapper;
import vic.cinema.model.Order;
import vic.cinema.model.ShoppingCart;
import vic.cinema.service.OrderService;
import vic.cinema.service.ShoppingCartService;
import vic.cinema.service.UserService;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService,
                           UserService userService,
                           ShoppingCartService shoppingCartService,
                           OrderMapper orderMapper) {
        this.orderService = orderService;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.orderMapper = orderMapper;
    }

    @PostMapping("/complete")
    public void completeOrder(@RequestParam Long userId) {
        ShoppingCart shoppingCart =
                shoppingCartService.getByUser(userService.get(userId));
        orderService.completeOrder(shoppingCart);
    }

    @GetMapping
    public List<OrderResponseDto> getAllUserOrders(@RequestParam Long userId) {
        List<Order> orderHistory = orderService.getOrderHistory(userService.get(userId));
        return orderHistory.stream().map(orderMapper::toDto).collect(Collectors.toList());
    }
}
