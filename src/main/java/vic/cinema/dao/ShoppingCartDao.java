package vic.cinema.dao;

import vic.cinema.model.ShoppingCart;
import vic.cinema.model.User;

public interface ShoppingCartDao {
    ShoppingCart add(ShoppingCart shoppingCart);

    ShoppingCart getByUser(User user);

    void update(ShoppingCart shoppingCart);

    ShoppingCart getByUserId(Long id);
}
