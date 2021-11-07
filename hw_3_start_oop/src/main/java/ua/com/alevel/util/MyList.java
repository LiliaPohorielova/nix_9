package ua.com.alevel.util;

import ua.com.alevel.entity.User;

public class MyList {

    User[] users;
    int maxCapacity;
    int countOfUsers;
    static final int DEFAULT_CAPACITY = 100;

    public MyList() {
        this(DEFAULT_CAPACITY);
    }

    public MyList(int capacity) {
        this.maxCapacity = capacity;
        users = new User[capacity];
        countOfUsers = 0;
    }

    public int getCountOfUsers() {
        return countOfUsers;
    }

    public User getUser(int i) {
        return users[i];
    }

    public void add(User a) {
        if (countOfUsers == maxCapacity) {
            User[] dataNew = new User[maxCapacity * 2];
            maxCapacity = maxCapacity * 2;
            for (int i = 0; i < countOfUsers; i++) {
                dataNew[i] = users[i];
            }
            users = dataNew;
        }
        users[countOfUsers] = a;
        countOfUsers++;
    }

    public void update(User a) {
        users[indexOfUser(a.getId())] = a;
    }

    public User[] getUsers() {
        return users;
    }

    public void deleteLastElement() {
        if (countOfUsers == 0) {
            throw new RuntimeException("list is empty: cannot delete");
        }
        countOfUsers--;
        users[countOfUsers] = null;
    }

    public void deleteFirstElement() {
        for (int i = 0; i < countOfUsers - 1; i++) {
            users[i] = users[i + 1];
        }
        users[countOfUsers - 1] = null;
        countOfUsers--;
    }

    public void delete(String id) {
        int indexOfDeletingUser = indexOfUser(id);
        users[indexOfDeletingUser] = null;
        for (int i = indexOfDeletingUser; i < countOfUsers - 1; i++) {
            users[i] = users[i + 1];
        }
        users[countOfUsers - 1] = null;
        countOfUsers--;
    }

    public int indexOfUser(String id) {
        for (int i = 0; i < countOfUsers; i++) {
            if (users[i].getId().equals(id)) {
                return i;
            }
        }
        throw new RuntimeException("user not found by id");
    }

    public MyList show() {
        MyList current = new MyList();
        for (int i = 0; i < countOfUsers; i++) {
            current.users = users;
        }
        return current;
    }
}
