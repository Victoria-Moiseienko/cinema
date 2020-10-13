package vic.cinema.service;

import java.util.List;
import vic.cinema.model.Order;
import vic.cinema.model.ShoppingCart;
import vic.cinema.model.User;

public interface OrderService {
    List<Order> getOrderHistory(User user);

    Order completeOrder(ShoppingCart cart);
}
