package ua.com.alevel.db;

import ua.com.alevel.entity.User;
import ua.com.alevel.util.MyList;

import java.util.UUID;

public final class UserDB {

    private final MyList users;
    private static UserDB instance;

    private UserDB() {
        users = new MyList();
    }

    public static UserDB getInstance() {
        if (instance == null) {
            instance = new UserDB();
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
        users.delete(id);
    }

    public User findById(String id) {
        for (int i = 0; i < users.getCountOfUsers(); i++) {
            if (users.getUser(i).getId().equals(id)) {
                return users.getUser(i);
            }
        }
        throw new RuntimeException("user not found by id");
    }

    public MyList findAll() {
        return users.show();
    }

    private String generateId() {
        String id = UUID.randomUUID().toString();
        for (int i = 0; i < users.getCountOfUsers(); i++) {
            if (users.getUser(i).getId().equals(id)) {
                return generateId();
            }
        }
        return id;
    }
}