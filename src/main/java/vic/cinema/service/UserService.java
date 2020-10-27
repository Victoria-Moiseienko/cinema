package vic.cinema.service;

import java.util.Optional;
import vic.cinema.model.User;

public interface UserService {
    User add(User user);

    Optional<User> findByEmail(String email);

    User get(Long id);
}
