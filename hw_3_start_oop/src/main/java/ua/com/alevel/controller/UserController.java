package ua.com.alevel.controller;

import ua.com.alevel.entity.User;
import ua.com.alevel.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ua.com.alevel.util.MyList;

public class UserController {

    private final UserService userService = new UserService();

    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String position;
        try {
            runNavigation();
            while ((position = reader.readLine()) != null) {
                crud(position, reader);
                position = reader.readLine();
                if (position.equals("0")) {
                    System.out.println("\n======================= EXIT ========================");
                    System.exit(0);
                }
                crud(position, reader);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void crud(String position, BufferedReader reader) {
        switch (position) {
            case "1" -> create(reader);
            case "2" -> update(reader);
            case "3" -> delete(reader);
            case "4" -> findById(reader);
            case "5" -> findAll(reader);
        }
        if (!position.equals("0")) {
            runNavigation();
        }
    }

    private void runNavigation() {
        System.out.println("\n===================== MAIN MENU =======================");
        System.out.println("Actions: ");
        System.out.println("1 - Create user");
        System.out.println("2 - Update user");
        System.out.println("3 - Delete user");
        System.out.println("4 - Find user by id");
        System.out.println("5 - Find all users");
        System.out.println("0 - Exit");
        System.out.print("\nThe action you want: ");
    }

    private void create(BufferedReader reader) {
        System.out.println("\n===================== CREATE USER =====================");
        try {
            System.out.println("Please, enter user`s name");
            String name = reader.readLine();
            System.out.println("Please, enter user`s age");
            String ageString = reader.readLine();
            int age = Integer.parseInt(ageString);
            User user = new User();
            user.setAge(age);
            user.setName(name);
            userService.create(user);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void update(BufferedReader reader) {
        System.out.println("\n===================== UPDATE USER =====================");
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();
            System.out.println("Please, enter user`s name");
            String name = reader.readLine();
            System.out.println("Please, enter user`s age");
            String ageString = reader.readLine();
            int age = Integer.parseInt(ageString);
            User user = new User();
            user.setId(id);
            user.setAge(age);
            user.setName(name);
            userService.update(user);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void delete(BufferedReader reader) {
        System.out.println("\n===================== DELETE USER =====================");
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();
            userService.delete(id);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void findById(BufferedReader reader) {
        System.out.println("\n===================== FIND USER BY ID =====================");
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();
            User user = userService.findById(id);
            System.out.println("User = " + user);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void findAll(BufferedReader reader) {
        System.out.println("\n===================== FIND ALL USERS =====================");
        MyList users = userService.findAll();
        boolean arrayIsEmpty = true;
        for (User user : users.getUsers()) {
            if (user != null) {
                arrayIsEmpty = false;
                System.out.println("user = " + user);
            }
        }
        if (arrayIsEmpty) {
            System.out.println("Error: Users empty");
        }
    }
}