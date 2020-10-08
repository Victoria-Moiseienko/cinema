package vic.cinema.service.impl;

import java.util.Optional;
import vic.cinema.dao.UserDao;
import vic.cinema.lib.Inject;
import vic.cinema.lib.Service;
import vic.cinema.model.User;
import vic.cinema.service.UserService;
import vic.cinema.util.HashUtil;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private UserDao userDao;

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
