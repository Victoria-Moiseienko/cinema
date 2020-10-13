package vic.cinema.service;

import java.util.List;
import vic.cinema.model.Order;
import vic.cinema.model.Ticket;
import vic.cinema.model.User;

public interface OrderService {
    List<Order> getOrderHistory(User user);

    Order completeOrder(List<Ticket> tickets, User user);
}
