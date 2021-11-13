package ua.com.alevel.controller.impl;

import ua.com.alevel.controller.DoctorController;
import ua.com.alevel.entity.Doctor;
import ua.com.alevel.service.impl.DoctorServiceImpl;
import ua.com.alevel.util.MyList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DoctorControllerImpl implements DoctorController {

    private final DoctorServiceImpl doctorService = new DoctorServiceImpl();

    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String position;
        try {
            runNavigation();
            while ((position = reader.readLine()) != null) {
                if (position.equals("0")) {
                    System.out.println("\n-------------------- EXIT DOCTOR ---------------------");
                    return;
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
        runNavigation();
    }

    private void runNavigation() {
        System.out.println("\n===================== DOCTOR MENU =======================");
        System.out.println("Actions: ");
        System.out.println("1 - Create doctor");
        System.out.println("2 - Update doctor");
        System.out.println("3 - Delete doctor");
        System.out.println("4 - Find doctor by id");
        System.out.println("5 - Find all doctors");
        System.out.println("0 - Exit");
        System.out.print("\nThe action you want: ");
    }

    private void create(BufferedReader reader) {
        System.out.println("\n===================== CREATE DOCTOR =====================");
        try {
            System.out.println("Please, enter doctor`s name");
            String name = reader.readLine();
            System.out.println("Please, enter doctor`s specialization");
            String specialization = reader.readLine();
            Doctor doctor = new Doctor();
            doctor.setSpecialization(specialization);
            doctor.setName(name);
            doctorService.create(doctor);
        } catch (IOException|RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void update(BufferedReader reader) {
        System.out.println("\n===================== UPDATE DOCTOR =====================");
        try {
            System.out.println(doctorService.findAll());
            System.out.println("Please, enter id (Choose one id of the options below)");
            String id = reader.readLine();
            System.out.println("Please, enter new doctor`s name");
            String name = reader.readLine();
            System.out.println("Please, enter new doctor`s specialization");
            String specialization = reader.readLine();
            Doctor doctor = new Doctor();
            doctor.setId(id);
            doctor.setSpecialization(specialization);
            doctor.setName(name);
            doctorService.update(doctor);
        } catch (IOException|RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void delete(BufferedReader reader) {
        System.out.println("\n===================== DELETE DOCTOR =====================");
        try {
            System.out.println(doctorService.findAll());
            System.out.println("Please, enter id (Choose one id of the options below)");
            String id = reader.readLine();
            doctorService.delete(id);
        } catch (IOException|RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void findById(BufferedReader reader) {
        System.out.println("\n===================== FIND DOCTOR BY ID =====================");
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();
            Doctor doctor = doctorService.findById(id);
            System.out.println("Doctor = " + doctor);
        } catch (IOException|RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void findAll(BufferedReader reader) {
        System.out.println("\n===================== FIND ALL DOCTORS =====================");
        MyList<Doctor> doctors = doctorService.findAll();
        if (doctors.getCountOfEntities() != 0) {
            for (int i = 0; i < doctors.getCountOfEntities(); i++) {
                System.out.println(doctors.getEntity(i));
            }
        } else {
            System.out.println("Error: Doctors empty");
        }
    }
}