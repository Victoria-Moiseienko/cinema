package vic.cinema.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import vic.cinema.dao.OrderDao;
import vic.cinema.model.Order;
import vic.cinema.model.ShoppingCart;
import vic.cinema.model.User;
import vic.cinema.service.OrderService;
import vic.cinema.service.ShoppingCartService;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao;
    private ShoppingCartService shoppingCartService;

    public OrderServiceImpl(OrderDao orderDao, ShoppingCartService shoppingCartService) {
        this.orderDao = orderDao;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        return orderDao.getAllByUser(user);
    }

    @Override
    public Order completeOrder(ShoppingCart cart) {
        Order order = new Order();
        order.setTickets(new ArrayList<>(cart.getTickets()));
        order.setUser(cart.getUser());
        order.setOrderDate(LocalDateTime.now());
        shoppingCartService.clear(cart);
        return orderDao.create(order);
    }
}
