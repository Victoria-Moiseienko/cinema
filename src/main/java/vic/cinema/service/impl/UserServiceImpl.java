package vic.cinema.service.impl;

import java.util.Optional;
import org.springframework.stereotype.Service;
import vic.cinema.dao.UserDao;
import vic.cinema.model.User;
import vic.cinema.service.UserService;
import vic.cinema.util.HashUtil;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User add(User user) {
        user.setSalt(HashUtil.getSalt());
        user.setPassword(HashUtil.hashPassword(user.getPassword(), user.getSalt()));
        return userDao.add(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
