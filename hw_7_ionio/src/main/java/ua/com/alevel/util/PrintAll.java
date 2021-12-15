package ua.com.alevel.util;

import ua.com.alevel.entity.Declaration;
import ua.com.alevel.entity.Doctor;
import ua.com.alevel.entity.Patient;
import ua.com.alevel.service.impl.DeclarationServiceImpl;
import ua.com.alevel.service.impl.DoctorServiceImpl;
import ua.com.alevel.service.impl.PatientServiceImpl;

import java.util.ArrayList;

public class PrintAll {

    public static void printAllDoctors(DoctorServiceImpl doctorService) {
        System.out.println("\nAll Doctors");
        ArrayList<Doctor> doctors = doctorService.findAll();
        if (doctors.size() != 0) {
            for (Doctor doctor : doctors) {
                System.out.println("Doctor's ID: " + doctor.getId());
                System.out.println("Doctor's Name: " + doctor.getName());
                System.out.println("Doctor's Specialization: " + doctor.getSpecialization() + "\n");
            }
        } else {
            throw new RuntimeException("Doctors empty!");
        }
    }

    public static void printAllPatients(PatientServiceImpl patientService) {
        System.out.println("\nAll Patients");
        ArrayList<Patient> patients = patientService.findAll();
        if (patients.size() != 0) {
            for (Patient patient : patients) {
                System.out.println("Patient's ID: " + patient.getId());
                System.out.println("Patient's Name: " + patient.getName());
                System.out.println("Patient's Age: " + patient.getAge() + "\n");
            }
        } else {
            throw new RuntimeException("Patients empty!");
        }
    }

    public static void printAllDeclarations(DeclarationServiceImpl declarationService) {
        System.out.println("\nAll Declarations");
        ArrayList<Declaration> declarations = declarationService.findAll();
        if (declarations.size() != 0) {
            for (Declaration declaration : declarations) {
                System.out.println("Declaration's ID: " + declaration.getId());
                System.out.println("Patient's ID: " + declaration.getIdPatient());
                System.out.println("Doctor's ID: " + declaration.getIdDoctor() + "\n");
            }
        } else {
            throw new RuntimeException("Declarations empty!");
        }
    }
}
