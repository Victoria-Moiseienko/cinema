package vic.cinema.service;

import vic.cinema.model.MovieSession;
import vic.cinema.model.ShoppingCart;
import vic.cinema.model.User;

public interface ShoppingCartService {
    void addSession(MovieSession movieSession, User user);

    ShoppingCart getByUser(User user);

    void registerNewShoppingCart(User user);

    void clear(ShoppingCart shoppingCart);

    ShoppingCart getByUserId(Long id);
}
