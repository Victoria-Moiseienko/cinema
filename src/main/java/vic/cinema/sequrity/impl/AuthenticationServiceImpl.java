package vic.cinema.sequrity.impl;

import java.util.Optional;
import javax.security.sasl.AuthenticationException;
import vic.cinema.lib.Inject;
import vic.cinema.lib.Service;
import vic.cinema.model.User;
import vic.cinema.sequrity.AuthenticationService;
import vic.cinema.service.ShoppingCartService;
import vic.cinema.service.UserService;
import vic.cinema.util.HashUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;
    @Inject
    private ShoppingCartService shoppingCartService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        Optional<User> userFromDB = userService.findByEmail(email);
        if (userFromDB.isPresent()
                && HashUtil.isValid(password,
                     userFromDB.get().getPassword(),
                     userFromDB.get().getSalt())) {
            return userFromDB.get();
        }
        throw new AuthenticationException("Incorrect email or password");
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        User userFromDB = userService.add(user);
        shoppingCartService.registerNewShoppingCart(userFromDB);
        return user;
    }
}
