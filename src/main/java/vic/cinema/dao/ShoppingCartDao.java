package vic.cinema.dao;

import java.util.Optional;
import vic.cinema.model.ShoppingCart;
import vic.cinema.model.User;

public interface ShoppingCartDao {
    ShoppingCart add(ShoppingCart shoppingCart);

    Optional<ShoppingCart> getByUser(User user);

    void update(ShoppingCart shoppingCart);
}
