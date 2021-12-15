package ua.com.alevel.db.impl;

import ua.com.alevel.db.PatientDB;
import ua.com.alevel.entity.Doctor;
import ua.com.alevel.entity.Patient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import static ua.com.alevel.ParseCSVUtil.getObjectsFromCSV;
import static ua.com.alevel.ParseCSVUtil.saveObjectToCSV;
import static ua.com.alevel.util.DeleteUtil.deleteInDB;
import static ua.com.alevel.util.FindByID.findObjectByID;

public final class PatientDBImpl implements PatientDB {

    private final ArrayList<Patient> patients;
    private static PatientDBImpl instance;

    private PatientDBImpl() {
        patients = new ArrayList<>();
    }

    public static PatientDBImpl getInstance() {
        if (instance == null) {
            instance = new PatientDBImpl();
        }
        return instance;
    }

    public void create(Patient patient) {
        patient.setId(generateId());
        try {
            saveObjectToCSV(patient);
        } catch (IOException e) {
            System.out.println("error = " + e.getMessage());
        }
    }

    public void update(Patient patient) {
        try {
            findObjectByID(Patient.class, patient.getId());
            deleteInDB(patient.getClass(), patient.getId());
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
        try {
            saveObjectToCSV(patient);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete(String id) {
        try {
            findObjectByID(Patient.class, id);
            deleteInDB(Patient.class, id);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }

    public Patient findById(String id) {
        try {
            return findObjectByID(Patient.class,id);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Not found Doctor's ID");
    }

    public ArrayList<Patient> findAll() {
        try {
            return getObjectsFromCSV(Patient.class);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String generateId() {
        String id = UUID.randomUUID().toString();
        for (Patient patient : patients) {
            if (patient.getId().equals(id)) {
                return generateId();
            }
        }
        return id;
    }
}