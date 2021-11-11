package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.UserDao;
import ua.com.alevel.db.impl.UserDBImpl;
import ua.com.alevel.entity.User;
import ua.com.alevel.util.MyList;

public class UserDaoImpl implements UserDao {

    public void create(User user) {
        UserDBImpl.getInstance().create(user);
    }

    public void update(User user) {
        UserDBImpl.getInstance().update(user);
    }

    public void delete(String id) {
        UserDBImpl.getInstance().delete(id);
    }

    public User findById(String id) {
        return UserDBImpl.getInstance().findById(id);
    }

    public MyList<User> findAll() {
        return UserDBImpl.getInstance().findAll();
    }
}