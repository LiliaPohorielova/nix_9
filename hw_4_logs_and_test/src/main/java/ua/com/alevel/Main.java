package ua.com.alevel;

import ua.com.alevel.controller.impl.UserControllerImpl;

public class Main {

    public static void main(String[] args) {
        try {
            new UserControllerImpl().run();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}