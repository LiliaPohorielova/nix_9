package ua.com.alevel;

import ua.com.alevel.controller.impl.DBControllerImpl;

public class Main {

    public static void main(String[] args) {
        try {
            new DBControllerImpl().run();
        } catch (Exception e) {
            System.out.println("MAIN Error: " + e.getMessage());
        }
    }
}