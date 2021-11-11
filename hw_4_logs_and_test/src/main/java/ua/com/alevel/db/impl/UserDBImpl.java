package ua.com.alevel.db.impl;

import ua.com.alevel.db.UserDB;
import ua.com.alevel.entity.User;
import ua.com.alevel.util.MyList;

import java.util.UUID;

public final class UserDBImpl implements UserDB {

    private final MyList<User> users;
    private static UserDBImpl instance;

    private UserDBImpl() {
        users = new MyList<>();
    }

    public static UserDBImpl getInstance() {
        if (instance == null) {
            instance = new UserDBImpl();
        }
        return instance;
    }

    public void create(User user) {
        user.setId(generateId());
        users.add(user);
    }

    public void update(User user) {
        user.getId();
        users.update(user);
    }

    public void delete(String id) {
        for (int i = 0; i < users.getCountOfEntities(); i++) {
            if (users.getUser(i).getId().equals(id)) {
                users.delete(i);
            }
        }

    }

    public User findById(String id) {
        for (int i = 0; i < users.getCountOfEntities(); i++) {
            if (users.getUser(i).getId().equals(id)) {
                return users.getUser(i);
            }
        }
        throw new RuntimeException("user not found by id");
    }

    public MyList<User> findAll() {
        return users;
    }

    private String generateId() {
        String id = UUID.randomUUID().toString();
        for (int i = 0; i < users.getCountOfEntities(); i++) {
            if (users.getUser(i).getId().equals(id)) {
                return generateId();
            }
        }
        return id;
    }
}