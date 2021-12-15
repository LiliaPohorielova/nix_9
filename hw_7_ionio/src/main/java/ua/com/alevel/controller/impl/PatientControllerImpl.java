package ua.com.alevel.controller.impl;

import ua.com.alevel.controller.PatientController;
import ua.com.alevel.entity.Patient;
import ua.com.alevel.service.impl.PatientServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static ua.com.alevel.util.PrintAll.printAllPatients;

public class PatientControllerImpl implements PatientController {

    private final PatientServiceImpl patientService = new PatientServiceImpl();

    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String position;
        try {
            runNavigation();
            while ((position = reader.readLine()) != null) {
                if (position.equals("0")) {
                    System.out.println("\n-------------------- EXIT PATIENT ---------------------");
                    return;
                }
                crud(position, reader);
            }
        } catch (IOException | RuntimeException e) {
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
        System.out.println("\n===================== PATIENT MENU =======================");
        System.out.println("Actions: ");
        System.out.println("1 - Create patient");
        System.out.println("2 - Update patient");
        System.out.println("3 - Delete patient");
        System.out.println("4 - Find patient by id");
        System.out.println("5 - Find all patients");
        System.out.println("0 - Exit");
        System.out.print("\nThe action you want: ");
    }

    private void create(BufferedReader reader) {
        System.out.println("\n===================== CREATE PATIENT =====================");
        try {
            System.out.println("Please, enter patient`s name");
            String name = reader.readLine();
            System.out.println("Please, enter patient`s age");
            String ageString = reader.readLine();
            int age = Integer.parseInt(ageString);
            Patient patient = new Patient();
            patient.setAge(age);
            patient.setName(name);
            patientService.create(patient);
        } catch (IOException | RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void update(BufferedReader reader) {
        System.out.print("\n===================== UPDATE PATIENT =====================");
        try {
            printAllPatients(patientService);
            System.out.println("Please, enter id (Choose one id of the options below)");
            String id = reader.readLine();
            System.out.println("Please, enter new patient`s name");
            String name = reader.readLine();
            System.out.println("Please, enter new patient`s age");
            String ageString = reader.readLine();
            int age = Integer.parseInt(ageString);
            Patient patient = new Patient();
            patient.setId(id);
            patient.setAge(age);
            patient.setName(name);
            patientService.update(patient);
        } catch (IOException | RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void delete(BufferedReader reader) {
        System.out.print("\n===================== DELETE PATIENT =====================");
        try {
            printAllPatients(patientService);
            System.out.println("Please, enter id (Choose one id of the options below)");
            String id = reader.readLine();
            patientService.delete(id);
        } catch (IOException | RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void findById(BufferedReader reader) {
        System.out.print("\n===================== FIND PATIENT BY ID =====================");
        try {
            printAllPatients(patientService);
            System.out.println("Please, enter id");
            String id = reader.readLine();
            Patient patient = patientService.findById(id);
            System.out.println("You Patient\n" + patient);
        } catch (IOException | RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void findAll(BufferedReader reader) {
        System.out.print("\n===================== FIND ALL PATIENTS =====================");
        printAllPatients(patientService);
    }
}