package vic.cinema.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import vic.cinema.dao.OrderDao;
import vic.cinema.lib.Inject;
import vic.cinema.lib.Service;
import vic.cinema.model.Order;
import vic.cinema.model.Ticket;
import vic.cinema.model.User;
import vic.cinema.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;

    @Override
    public List<Order> getOrderHistory(User user) {
        return orderDao.getAllByUser(user);
    }

    @Override
    public Order completeOrder(List<Ticket> tickets, User user) {
        Order order = new Order();
        order.setTickets(tickets);
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        return orderDao.create(order);
    }
}
