package vic.cinema.dao;

import java.util.List;
import vic.cinema.model.Order;
import vic.cinema.model.User;

public interface OrderDao {
    List<Order> getAllByUser(User user);

    Order create(Order order);
}
