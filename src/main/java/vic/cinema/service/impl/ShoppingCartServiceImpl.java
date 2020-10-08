package vic.cinema.service.impl;

import java.util.ArrayList;
import java.util.Optional;
import vic.cinema.dao.ShoppingCartDao;
import vic.cinema.dao.TicketDao;
import vic.cinema.exeptions.DataProcessingException;
import vic.cinema.lib.Inject;
import vic.cinema.lib.Service;
import vic.cinema.model.MovieSession;
import vic.cinema.model.ShoppingCart;
import vic.cinema.model.Ticket;
import vic.cinema.model.User;
import vic.cinema.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Inject
    private ShoppingCartDao shoppingCartDao;
    @Inject
    private TicketDao ticketDao;

    @Override
    public void addSession(MovieSession movieSession, User user) {
        Ticket ticket = new Ticket();
        ticket.setMovieSession(movieSession);
        ticket.setUser(user);
        ticketDao.add(ticket);
        ShoppingCart shoppingCart = getByUser(user);
        shoppingCart.getTickets().add(ticket);
        shoppingCartDao.update(shoppingCart);
    }

    @Override
    public ShoppingCart getByUser(User user) {
        Optional<ShoppingCart> cartByUser = shoppingCartDao.getByUser(user);
        if (cartByUser.isPresent()) {
            return cartByUser.get();
        }
        throw new DataProcessingException("Shopping cart for user["
                + user.getEmail() + "] has not been found");
    }

    @Override
    public void registerNewShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCartDao.add(shoppingCart);
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCart.setTickets(new ArrayList<>());
        shoppingCartDao.update(shoppingCart);
    }
}
