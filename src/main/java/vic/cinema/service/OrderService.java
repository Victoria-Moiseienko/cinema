package vic.cinema.service;

import java.util.List;
import vic.cinema.model.Order;
import vic.cinema.model.Ticket;
import vic.cinema.model.User;

public interface OrderService {
    Order completeOrder(List<Ticket> tickets, User user);

    List<Order> getOrderHistory(User user);
}
