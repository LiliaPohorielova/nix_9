package ua.com.alevel.service;

import ua.com.alevel.entity.Declaration;
import ua.com.alevel.entity.Doctor;
import ua.com.alevel.entity.Patient;
import ua.com.alevel.service.impl.DeclarationServiceImpl;
import ua.com.alevel.service.impl.DoctorServiceImpl;
import ua.com.alevel.service.impl.PatientServiceImpl;

import java.util.ArrayList;

public class GenerationUtil {
    public static final String NAME_OF_PATIENT = "test patient";
    public static final String NAME_OF_DOCTOR = "test doctor";
    public static final int AGE_OF_PATIENT = 25;
    public static final String SPECIALIZATION_OF_DOCTOR = "dentist";

    private final static PatientServiceImpl patientServiceImpl = new PatientServiceImpl();
    private final static DoctorServiceImpl doctorServiceImpl = new DoctorServiceImpl();
    private final static DeclarationServiceImpl declarationServiceImpl = new DeclarationServiceImpl();

    public static Patient generatePatient(String name, int age) {
        Patient patient = new Patient();
        patient.setName(name);
        patient.setAge(age);
        return patient;
    }

    public static Doctor generateDoctor(String name, String spec) {
        Doctor doctor = new Doctor();
        doctor.setName(name);
        doctor.setSpecialization(spec);
        return doctor;
    }

    public static Declaration generateDeclaration(String idDoctor, String idPatient) {
        Declaration declaration = new Declaration();
        declaration.setIdPatient(idPatient);
        declaration.setIdDoctor(idDoctor);
        return declaration;
    }

    public static void clearDoctors() {
        ArrayList<Doctor> doctors = doctorServiceImpl.findAll();
        for (int i = 0; i < doctors.size(); i++) {
            doctorServiceImpl.delete(doctors.get(i).getId());
        }
        doctors.clear();
    }

    public static void clearPatients() {
        ArrayList<Patient> patients = patientServiceImpl.findAll();
        for (int i = 0; i < patients.size(); i++) {
            patientServiceImpl.delete(patients.get(i).getId());
        }
        patients.clear();
    }

    public static void clearAll() {
        ArrayList<Declaration> declarations = declarationServiceImpl.findAll();
        for (int i = 0; i < declarations.size(); i++) {
            declarationServiceImpl.delete(declarations.get(i).getId());
        }
        declarations.clear();
        ArrayList<Patient> patients = patientServiceImpl.findAll();
        for (int i = 0; i < patients.size(); i++) {
            patientServiceImpl.delete(patients.get(i).getId());
        }
        patients.clear();
        ArrayList<Doctor> doctors = doctorServiceImpl.findAll();
        for (int i = 0; i < doctors.size(); i++) {
            doctorServiceImpl.delete(doctors.get(i).getId());
        }
        doctors.clear();
    }
}
