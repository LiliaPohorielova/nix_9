package ua.com.alevel.service.impl;

import ua.com.alevel.dao.impl.UserDaoImpl;
import ua.com.alevel.entity.User;
import ua.com.alevel.service.UserService;
import ua.com.alevel.util.MyList;

public class UserServiceImpl implements UserService {

    private final UserDaoImpl userDao = new UserDaoImpl();

    public void create(User user) {
        userDao.create(user);
    }

    public void update(User user) {
        userDao.update(user);
    }

    public void delete(String id) {
        userDao.delete(id);
    }

    public User findById(String id) {
        return userDao.findById(id);
    }

    public MyList<User> findAll() {
        return userDao.findAll();
    }
}