package ua.com.alevel;

import ua.com.alevel.controller.UserController;

public class Main {

    public static void main(String[] args) {
        try {
            new UserController().run();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}