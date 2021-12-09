package ua.com.alevel.controller.impl;

import ua.com.alevel.controller.DBController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DBControllerImpl implements DBController {

    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String position;
        try {
            runNavigation();
            while ((position = reader.readLine()) != null) {
                if (position.equals("0")) {
                    System.out.println("========================= EXIT =========================");
                    System.exit(0);
                }
                crud(position);
            }
        } catch (IOException | RuntimeException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void runNavigation() {
        System.out.println("\n===================== HEALTHY PROJECT =======================");
        System.out.println("Entities: ");
        System.out.println("1 - DOCTOR");
        System.out.println("2 - PATIENT");
        System.out.println("3 - DECLARATION");
        System.out.println("0 - EXIT");
        System.out.print("\nThe entity you want: ");
    }

    private void crud(String position) {
        switch (position) {
            case "1" -> new DoctorControllerImpl().run();
            case "2" -> new PatientControllerImpl().run();
            case "3" -> new DeclarationControllerImpl().run();
        }
        runNavigation();
    }
}
