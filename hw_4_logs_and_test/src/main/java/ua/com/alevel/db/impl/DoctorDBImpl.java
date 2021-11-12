package ua.com.alevel.db.impl;

import ua.com.alevel.db.DoctorDB;
import ua.com.alevel.entity.Doctor;
import ua.com.alevel.entity.Patient;
import ua.com.alevel.util.MyList;

import java.util.UUID;

public class DoctorDBImpl implements DoctorDB {

    private final MyList<Doctor> doctors;
    private static DoctorDBImpl instance;

    private DoctorDBImpl() {
        doctors = new MyList<>();
    }

    public static DoctorDBImpl getInstance() {
        if (instance == null) {
            instance = new DoctorDBImpl();
        }
        return instance;
    }

    public void create(Doctor doctor) {
        doctor.setId(generateId());
        doctors.add(doctor);
    }

    public void update(Doctor doctor) {
        Doctor temp = findById(doctor.getId());
        temp.setName(doctor.getName());
        temp.setSpecialization(doctor.getSpecialization());
    }

    public void delete(String id) {
        for (int i = 0; i < doctors.getCountOfEntities(); i++) {
            if (doctors.getEntity(i).getId().equals(id)) {
                doctors.delete(i);
            }
        }

    }

    public Doctor findById(String id) {
        for (int i = 0; i < doctors.getCountOfEntities(); i++) {
            if (doctors.getEntity(i).getId().equals(id)) {
                return doctors.getEntity(i);
            }
        }
        throw new RuntimeException("Doctor not found by id");
    }

    public MyList<Doctor> findAll() {
        return doctors;
    }

    public void showAllToConsole() {
        if (doctors.getCountOfEntities() != 0) {
            for (int i = 0; i < doctors.getCountOfEntities(); i++) {
                System.out.println(doctors.getEntity(i));
            }
        } else {
            System.out.println("[empty]");
        }
    }

    private String generateId() {
        String id = UUID.randomUUID().toString();
        for (int i = 0; i < doctors.getCountOfEntities(); i++) {
            if (doctors.getEntity(i).getId().equals(id)) {
                return generateId();
            }
        }
        return id;
    }
}