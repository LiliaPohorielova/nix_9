package ua.com.alevel.controller.impl;

import ua.com.alevel.controller.DeclarationController;
import ua.com.alevel.entity.Declaration;
import ua.com.alevel.service.impl.DeclarationServiceImpl;
import ua.com.alevel.service.impl.DoctorServiceImpl;
import ua.com.alevel.service.impl.PatientServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static ua.com.alevel.util.PrintAll.printAllDeclarations;
import static ua.com.alevel.util.PrintAll.printAllPatients;
import static ua.com.alevel.util.PrintAll.printAllDoctors;

public class DeclarationControllerImpl implements DeclarationController {

    private final DeclarationServiceImpl declarationService = new DeclarationServiceImpl();
    private final PatientServiceImpl patientService = new PatientServiceImpl();
    private final DoctorServiceImpl doctorService = new DoctorServiceImpl();

    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String position;
        try {
            runNavigation();
            while ((position = reader.readLine()) != null) {
                if (position.equals("0")) {
                    System.out.println("\n-------------------- EXIT DECLARATION ---------------------");
                    break;
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
        runNavigation();
    }

    private void runNavigation() {
        System.out.println("\n===================== DECLARATION MENU =======================");
        System.out.println("Actions: ");
        System.out.println("1 - Create declaration");
        System.out.println("2 - Update declaration");
        System.out.println("3 - Delete declaration");
        System.out.println("4 - Find declaration by id");
        System.out.println("5 - Find all declaration");
        System.out.println("0 - Exit");
        System.out.print("\nThe action you want: ");
    }

    private void create(BufferedReader reader) {
        System.out.print("\n===================== CREATE DECLARATION =====================");
        try {
            printAllPatients(patientService);
            System.out.println("Please, enter patient`s id (Choose one id of the options below)");
            String idPatient = reader.readLine();
            Declaration declaration = new Declaration();
            declaration.setIdPatient(patientService.findById(idPatient).getId());
            printAllDoctors(doctorService);
            System.out.println("Please, enter doctor`s id (Choose one id of the options below)");
            String idDoctor = reader.readLine();
            declaration.setIdDoctor(doctorService.findById(idDoctor).getId());
            declarationService.create(declaration);
        } catch (RuntimeException | IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void update(BufferedReader reader) {
        System.out.print("\n===================== UPDATE DECLARATION =====================");
        try {
            printAllDeclarations(declarationService);
            System.out.println("Please, enter id of declaration (Choose one id of the options below)");
            String id = reader.readLine();
            Declaration declaration = new Declaration();
            declaration.setId(id);
            printAllPatients(patientService);
            System.out.println("Please, enter patient`s id (Choose one id of the options below)");
            String idPatient = reader.readLine();
            declaration.setIdPatient(patientService.findById(idPatient).getId());
            printAllDoctors(doctorService);
            System.out.println("Please, enter doctor`s id (Choose one id of the options below)");
            String idDoctor = reader.readLine();
            declaration.setIdDoctor(doctorService.findById(idDoctor).getId());
            declarationService.update(declaration);
        } catch (IOException | RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void delete(BufferedReader reader) {
        System.out.print("\n===================== DELETE DECLARATION =====================");
        try {
            printAllDeclarations(declarationService);
            System.out.println("Please, enter id (Choose one id of the options below)");
            String id = reader.readLine();
            declarationService.delete(id);
        } catch (IOException | RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void findById(BufferedReader reader) {
        System.out.print("\n===================== FIND DECLARATION BY ID =====================");
        try {
            printAllDeclarations(declarationService);
            System.out.println("Please, enter id");
            String id = reader.readLine();
            Declaration declaration = declarationService.findById(id);
            System.out.println("Your Declaration\n" + declaration);
        } catch (IOException | RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void findAll(BufferedReader reader) {
        System.out.print("\n===================== FIND ALL DECLARATIONS =====================");
        printAllDeclarations(declarationService);
    }
}