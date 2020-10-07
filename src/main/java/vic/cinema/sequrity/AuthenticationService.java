package vic.cinema.sequrity;

import javax.security.sasl.AuthenticationException;
import vic.cinema.model.User;

public interface AuthenticationService {
    User login(String email, String password) throws AuthenticationException;

    User register(String email, String password);
}
