package ua.com.alevel.db.impl;

import ua.com.alevel.db.DoctorDB;
import ua.com.alevel.entity.Doctor;
import static ua.com.alevel.ParseCSVUtil.*;
import static ua.com.alevel.util.DeleteUtil.deleteInDB;
import static ua.com.alevel.util.FindByID.findObjectByID;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class DoctorDBImpl implements DoctorDB {

    private final ArrayList<Doctor> doctors;
    private static DoctorDBImpl instance;

    private DoctorDBImpl() {
        doctors = new ArrayList<>();
    }

    public static DoctorDBImpl getInstance() {
        if (instance == null) {
            instance = new DoctorDBImpl();
        }
        return instance;
    }

    public void create(Doctor doctor) {
        doctor.setId(generateId());
        try {
            saveObjectToCSV(doctor);
        } catch (IOException e) {
            System.out.println("error = " + e.getMessage());
        }
    }

    public void update(Doctor doctor) {
        try {
            findObjectByID(Doctor.class, doctor.getId());
            deleteInDB(doctor.getClass(), doctor.getId());
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
        try {
            saveObjectToCSV(doctor);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete(String id) {
        try {
            findObjectByID(Doctor.class, id);
            deleteInDB(Doctor.class, id);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }

    public Doctor findById(String id) {
        try {
            return findObjectByID(Doctor.class,id);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Not found Doctor's ID");
    }

    public ArrayList<Doctor> findAll() {
        try {
            return getObjectsFromCSV(Doctor.class);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String generateId() {
        String id = UUID.randomUUID().toString();
        for (Doctor doctor : doctors) {
            if (doctor.getId().equals(id)) {
                return generateId();
            }
        }
        return id;
    }
}