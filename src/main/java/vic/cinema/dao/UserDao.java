package vic.cinema.dao;

import java.util.Optional;
import vic.cinema.model.User;

public interface UserDao {
    User add(User user);

    Optional<User> findByEmail(String email);

    User get(Long id);
}
