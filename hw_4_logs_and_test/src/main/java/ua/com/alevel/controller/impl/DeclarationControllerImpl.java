package ua.com.alevel.controller.impl;

import ua.com.alevel.controller.DeclarationController;
import ua.com.alevel.entity.Declaration;
import ua.com.alevel.service.DoctorService;
import ua.com.alevel.service.PatientService;
import ua.com.alevel.service.DeclarationService;
import ua.com.alevel.service.impl.DeclarationServiceImpl;
import ua.com.alevel.service.impl.DoctorServiceImpl;
import ua.com.alevel.service.impl.PatientServiceImpl;
import ua.com.alevel.util.MyList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DeclarationControllerImpl implements DeclarationController {

    private final DeclarationService declarationService = new DeclarationServiceImpl();
    private final PatientService patientService = new PatientServiceImpl();
    private final DoctorService doctorService = new DoctorServiceImpl();
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
        } catch (IOException|RuntimeException e) {
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
        // if (!position.equals("0")) {
        runNavigation();
        //}
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
        System.out.println("\n===================== CREATE DECLARATION =====================");
        try {
            System.out.println(patientService.findAll());
            System.out.println("Please, enter patient`s id (Choose one id of the options below)");
            String idPatient = reader.readLine();
            Declaration declaration = new Declaration();
            declaration.setIdPatient(patientService.findById(idPatient).getId());
            System.out.println(doctorService.findAll());
            System.out.println("Please, enter doctor`s id (Choose one id of the options below)");
            String idDoctor = reader.readLine();
            declaration.setIdDoctor(doctorService.findById(idDoctor).getId());
            declarationService.create(declaration);
        } catch (RuntimeException|IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void update(BufferedReader reader) {
        System.out.println("\n===================== UPDATE DECLARATION =====================");
        try {
            System.out.println(declarationService.findAll());
            System.out.println("Please, enter id of declaration (Choose one id of the options below)");
            String id = reader.readLine();
            Declaration declaration = new Declaration();
            declaration.setId(id);
            System.out.println(patientService.findAll());
            System.out.println("Please, enter patient`s id (Choose one id of the options below)");
            String idPatient = reader.readLine();
            declaration.setIdPatient(patientService.findById(idPatient).getId());
            System.out.println(doctorService.findAll());
            System.out.println("Please, enter doctor`s id (Choose one id of the options below)");
            String idDoctor = reader.readLine();
            declaration.setIdDoctor(doctorService.findById(idDoctor).getId());
            declarationService.update(declaration);
        } catch (IOException|RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void delete(BufferedReader reader) {
        System.out.println("\n===================== DELETE DECLARATION =====================");
        try {
            System.out.println(declarationService.findAll());
            System.out.println("Please, enter id (Choose one id of the options below)");
            String id = reader.readLine();
            declarationService.delete(id);
        } catch (IOException|RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void findById(BufferedReader reader) {
        System.out.println("\n===================== FIND DECLARATION BY ID =====================");
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();
            Declaration declaration = declarationService.findById(id);
            System.out.println("Declaration = " + declaration);
        } catch (IOException|RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void findAll(BufferedReader reader) {
        System.out.println("\n===================== FIND ALL DECLARATIONS =====================");
        MyList<Declaration> declarations = declarationService.findAll();
        if (declarations.getCountOfEntities() != 0) {
            for (int i = 0; i < declarations.getCountOfEntities(); i++) {
                System.out.println(declarations.getEntity(i));
            }
        } else {
            System.out.println("Error: Declarations empty");
        }
    }
}