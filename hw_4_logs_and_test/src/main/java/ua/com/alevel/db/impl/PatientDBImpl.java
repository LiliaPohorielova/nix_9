package ua.com.alevel.db.impl;

import ua.com.alevel.db.PatientDB;
import ua.com.alevel.entity.Patient;
import ua.com.alevel.util.MyList;

import java.util.UUID;

public final class PatientDBImpl implements PatientDB {

    private final MyList<Patient> patients;
    private static PatientDBImpl instance;

    private PatientDBImpl() {
        patients = new MyList<>();
    }

    public static PatientDBImpl getInstance() {
        if (instance == null) {
            instance = new PatientDBImpl();
        }
        return instance;
    }

    public void create(Patient patient) {
        patient.setId(generateId());
        patients.add(patient);
    }

    public void update(Patient patient) {
        Patient temp = findById(patient.getId());
        temp.setName(patient.getName());
        temp.setAge(patient.getAge());
    }

    public void delete(String id) {
        for (int i = 0; i < patients.getCountOfEntities(); i++) {
            if (patients.getEntity(i).getId().equals(id)) {
                patients.delete(i);
            }
        }

    }

    public Patient findById(String id) {
        for (int i = 0; i < patients.getCountOfEntities(); i++) {
            if (patients.getEntity(i).getId().equals(id)) {
                return patients.getEntity(i);
            }
        }
        throw new RuntimeException("Patient not found by id");
    }

    public MyList<Patient> findAll() {
        return patients;
    }

    private String generateId() {
        String id = UUID.randomUUID().toString();
        for (int i = 0; i < patients.getCountOfEntities(); i++) {
            if (patients.getEntity(i).getId().equals(id)) {
                return generateId();
            }
        }
        return id;
    }
}