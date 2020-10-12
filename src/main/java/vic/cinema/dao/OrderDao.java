package vic.cinema.dao;

import java.util.List;
import vic.cinema.model.Order;
import vic.cinema.model.User;

public interface OrderDao {
    Order create(Order order);

    List<Order> getAllByUser(User user);
}
